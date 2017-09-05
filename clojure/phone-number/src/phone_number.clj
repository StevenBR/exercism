(ns phone-number)

(defn- parse-number
  [number]
  (->> number
       (re-seq #"[0-9]+")
       (apply str)))

(defn parse-us-number
  [n]
  (if (and (= (count n) 11) (= (first n) \1))
                (drop 1 n)
                (repeat 10 "0")))

(defn number
  [num]
  (let [n (parse-number num)]
    (->> (if (= 10 (count n))
            n
            (parse-us-number n))
        (apply str))))

(defn area-code
  [num]
  (-> num
       number
       (subs 0 3)))

(defn pretty-print
  [num]
  (let [n (number num)]
      (format "(%s) %s-%s" (subs n 0 3) (subs n 3 6) (subs n 6 10))))

