(ns phone-number)

(defn parse-number
  [number]
  (->> number
       (re-seq #"[0-9]+")
       (apply str)))

(defn something
  []
  (->> "1(503)602-0442"
      parse-number
      (as-> num
            num)))

(defn number
  [num]
  (let [n (parse-number num)]
    (->> (if (= 10 (count n))
        n
        (if (and (= (count n) 11) (= (first n) \1))
            (drop 1 n)
            (repeat 10 "0")))
        (apply str))))

(something)
(drop 1 (parse-number "1(503)602-0442"))
(drop 1 (parse-number "(503)602-0442"))
(if (= 10 (count (parse-number "(503)602-0442")))
  (print "this was true ********")
(if (and (= 11 (count (parse-number "1(503)602-0442"))) (= \1 (first (parse-number "1(503)602-0442"))))
  (drop 1 (something))
  (print "you*****")))
(number "1(503)602-0442")
(number "(503)602-0442")

(defn size
  [num]
  (count num))

;(defn number
;  [num]
;  (-> num
;      number))

(defn area-code
  [num]
  (let [size (count num)]
  (->> num
       parse-number
       (take 3)
       (apply str))))

(defn pretty-print
  [])

