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

(def yak-butter-internation
  {:store "Yak Butter International"
   :price 90
   :smoothness 90})
(def butter-than-nothing
  {:store "Butter Than Nothing"
   :price 150
   :smoothness 83})
(def baby-got-yak
  {:store "Baby Got Yak"
   :price 94
   :smoothness 99})

(defn mock-api-call
  [result]
  (Thread/sleep 1000)
  result)

(defn satisfactory?
  [butter]
  (and (<= (:price butter) 100)
       (>= (:smoothness butter) 97)
       butter))
