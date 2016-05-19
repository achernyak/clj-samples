(require '[clojure.zip :as z])

(defn html-zip [root]
  (z/zipper
   vector?
   (fn [[tagname & xs]]
     (if (map? (first xs)) (next xs) xs))
   (fn [[tagname & xs] children]
     (into (if (map? (first xs)) [tagname (first xs)] [tagname])
           children))
   root))
