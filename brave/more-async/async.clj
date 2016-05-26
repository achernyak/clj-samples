(def alphabet-length 26)

(def letters (mapv (comp str char (partial + 65)) (range alphabet-length)))

(defn random-string
  "Returns a random string of specified length"
  [length]
  (apply str (take length (repeatedly #(rand-nth letters)))))

(defn random-string-list
  [list-length string-length]
  (doall (take list-length (repeatedly (partial random-string string-length)))))

(defn ppmap
  "Partitioned pmap, for grouping map ops together to make parallel
  overhead worthwhile"
  [grain-size f & colls]
  (apply concat
         (apply pmap
                (fn [& pgroups] (doall (apply map f pgroups)))
                (map (partial partition-all grain-size) colls))))

(defn quote-request
  []
  (future (slurp "http://www.braveclojure.com/random-quote")))

(defn word-count
  [string]
  (count (clojure.string/split string #" ")))

(def total-count (atom 0))

(defn quote-word-count
  [quote-num word-count]
  (let [futures (repeatedly quote-num quote-request)]
    (map (fn [new-count]
             (swap! word-count + new-count))
         (map word-count (map deref futures)))))

(defn quote-counter
  [quote-num]
  (let [word-count (atom 0)]
      (if (quote-word-count quote-num word-count)
      (println @word-count))))

(quote-counter 5)
