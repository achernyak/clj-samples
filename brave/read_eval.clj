(eval
 (let [infix (read-string "(1 + 1)")]
   (list (second infix) (first infix) (last infix))))

(defmacro infix
  "Use this macro when you pine for a notation of your childhood"
  [infixed]
  (list (second infixed) (first infixed) (last infixed)))
