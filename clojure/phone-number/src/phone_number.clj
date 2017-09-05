(ns phone-number)

(defn- parse-number
  [number]
  (->> number
       (re-seq #"[0-9]+")
       (apply str)))

(defn- us-number?
  [n]
  (and (= (count n) 11) (= (first n) \1)))

(defn- parse-us-number
  [n]
  (if (us-number? n)
      (subs n 1)
      "0000000000"))

(defn number
  [num]
  (let [n (parse-number num)]
    (if (= 10 (count n))
            n
            (parse-us-number n))))

(defn area-code
  [num]
  (-> num
       number
       (subs 0 3)))

(defn pretty-print
  [num]
  (let [n (number num)]
      (format "(%s) %s-%s" (area-code n) (subs n 3 6) (subs n 6 10))))

