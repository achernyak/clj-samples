(def ^:dynamic *notification-address* "dobby@elf.org")

(binding [*notification-address* "tester-1@elf.org"]
  (println *notification-address*)
  (binding [*notification-address* "tester-2@elf.org"]
    (println *notification-address*))
  (println *notification-address*))

(defn notify
  [message]
  (str "TO: " *notification-address* "\n"
       "Message: " message))
(notify "I fell.")

(binding [*notification-address* "Test@elf.org"]
  (notify "test!"))
