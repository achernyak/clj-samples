(def gimli-headshots ["serious.jpg" "fun.jpg" "playful.jpg"])

(defn email-user
  [email-address]
  (println "Sending headhshot notification to" email-address))

(defn upload-document
  "Needs to be implementation"
  [headshot]
  true)

(let [notify (delay (email-user "and-my-axe@gmail.com"))]
      (doseq [headshot gimli-headshots]
        (future (upload-document headshot)
                (force notify))))
