;;Опишите функцию, возвращающую все возрастающие подпоследовательности заданной бесконечной последовательности.
(defn all-subsequences [coll]
  (letfn [(subseq [coll]
            (lazy-seq
              (when-let [s (seq coll)]
                (cons s
                      (lazy-cat (subseq (rest s))
                                (subseq (rest coll)))))))]
    (subseq coll)))

;; Бесконечная последовательность натуральных чисел
(def infinite-sequence (iterate inc 1))

;; Используем функцию для вывода первых 10 подпоследовательностей
(doseq [subseq (take 10 (all-subsequences infinite-sequence))]
  (println subseq))
