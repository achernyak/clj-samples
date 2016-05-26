(def fred (atom {:hunger-level 0
                 :precent-deteriorated 0}))
(swap! fred
       (fn [current-state]
         (merge-with + current-state {:hunger-level 1
                                      :percent-deteriorated 1})))
(defn increase-hunger-level
  [zombie-state increase-by]
  (merge-with + zombie-state {:hunger-level increase-by}))

(swap! fred increase-hunger-level 10)

(swap! fred update-in [:hunger-level] + 10)

(reset! fred {:hunger-level 0
              :percent-deteriorated 0})

(defn shuffle-speed
  [zombie]
  (* (:hunger-level zombie)
     (- 100 (:percent-deteriorated zombie))))

(defn shuffle-alert
  [key watched old-state new-state]
  (let [sph (shuffle-speed new-state)]
    (if (> sph 5000)
      (do
        (println "Run you fool!")
        (println "The zombie's SPH is now " sph))
      (do
        (println "Alls well with " key)
        (println "SPH: " sph)))))

(reset! fred {:hunger-level 22
              :percent-deteriorated 2})

(add-watch fred :fred-suffle-alert shuffle-alert)

(swap! fred update-in [:percent-deteriorated] + 1)
(swap! fred update-in [:hunger-level] + 30)

(defn percent-deteriorated-validator
  [{:keys [percent-deteriorated]}]
  (or (and (>= percent-deteriorated 0)
           (<= percent-deteriorated 100))
      (throw (IllegalStateException. "That's not mathy!"))))

(def bobby
  (atom
   {:hunger-level 0 :percent-deteriorated 0}
   :validator percent-deteriorated-validator))

