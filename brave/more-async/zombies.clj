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

(reset! fred {:hungler-level 0
              :percent-deteriorated 0})
