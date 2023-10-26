;;Опишите функцию, группирующую записи блогов по тегам (у записи может быть более одного тега), внутри группы записи должны быть отсортированы по дате.
(defn group-blog-entries [blog-entries]
  (let [grouped-blog-entries (for [entry blog-entries
                               tag (:tags entry)]
                           (assoc entry :tag tag))]
    (let [grouped (group-by :tag grouped-blog-entries)]
      (into {}
            (for [[tag entries] grouped]
              [tag (sort-by :date entries)])))))

(defn example-usage []
  (def blog-entries
    [{:title "№ 1", :tags ["JavaScript", "Clojure"], :date "2023-10-23"}
     {:title "№ 2", :tags ["Clojure", "Python"], :date "2023-10-24"}
     {:title "№ 3", :tags ["Python", "Java"], :date "2023-10-21"}
     {:title "№ 4", :tags ["Java", "JavaScript"], :date "2023-10-26"}])
  
  (let [grouped-entries (group-blog-entries blog-entries)]
    (doseq [[tag entries] grouped-entries]
      (println (str "Tag: " tag))
      (doseq [entry entries]
        (println (str "  - " (:title entry) " (" (:date entry) ")")))))
  )
(example-usage)
