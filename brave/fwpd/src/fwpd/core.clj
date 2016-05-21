(ns fwpd.core)
(def filename "suspects.csv")

(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity
                  :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))


(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

(defn list-names
  "Takes a seq of map with :name and returns a seq of names"
  [rows]
  (map #(:name %) rows))

(defn glitter-filter
  [minimum-glitter records]
  (list-names(filter #(>= (:glitter-index %) minimum-glitter) records)))

(def validations {:name string?
                  :glitter-index integer?})

(defn validate
  "Checks if a record is valid"
  [validators records]
  (not (some false?
             (map (fn [validator]
                    ((val validator)
                     ((key validator) records)))
                  validators))))

(defn append
  [suspect-list suspect]
  (conj suspect-list suspect))

(defn join-record
  [record]
  (clojure.string/join ","
                       (map (fn [field]
                              (-> field second str))
                            record)))


(defn csv
  [records]
  (clojure.string/join "\n" (map join-record records)))
