(ns clock)

(def hours-in-seconds (* 60 60 24))

(defn- seconds->minutes
    [seconds]
      (/ seconds 60))

(defn- hours->seconds
    [hours]
      (* hours 60 60))

(defn- minutes->hours
    [minutes]
      (quot minutes 60))

(defn- minutes->seconds
    [minutes]
      (->>  minutes
                  (map (partial * 60))
                         (apply +)))

(defn- total-seconds
    [hours & minutes]
      (-> (minutes->seconds minutes)
                (+ (hours->seconds hours))
                      (mod hours-in-seconds)))

(defn clock->string
    [[hour minutes]]
      (format "%02d:%02d" hour minutes))

(defn clock
    [h m]
      (-> (total-seconds h m)
                seconds->minutes
                      (as-> mins
                                [(minutes->hours mins) (mod mins 60)])))

(defn add-time
    [[h m] a]
      (->> (total-seconds h m a)
                  seconds->minutes
                         (clock 0)))


