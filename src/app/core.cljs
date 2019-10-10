(ns app.core
  (:require [reagent.core :as r]))

(defonce data (r/atom {:prefixes ["Air" "Jet" "Flight"]
                   :sufixes ["Hub" "Station" "Mart"]
                   :domains ["Airhub" "AirStation" "AirMart" "JetHub"
                             "JetStation" "JetMart" "FlightHub" "FlighStation"
                             "FlightMart"]}))

(defonce domains (r/atom [])) ;; Utilizar para implementar VIdeo #3

(defn header
  [] 
  [:div {:id "slogan" :class "text-center"} 
   [:h1 "Name Generator"]
   [:h6.text-secondary "Gerador de nomes utilizando ClojureScript e Clojure"]])

#_(defn ix-input 
  [valor placeholder]
  [:input {:class "form-control" 
           :type "text"
           :placeholder placeholder
           :value @valor
           :on-change #(reset! valor (-> % .-target .-value))}])

;; TODO: Video #2: Utilizar método do splice de JS para remover item do array
;;       verificar cljs->js, js/....

(defn delete-ix 
  [valor kw-ix] 
  (swap! data assoc kw-ix (into [] (filter #(not (= % valor)) (kw-ix @data)))))

;; TODO: Video #2 - Adicionar evento on-keyup.enter ao digitar o prefixo/sufixo

(defn delete-button 
  [valor kw-ix]
  [:button {:class "btn btn-info"} 
   [:span {:class "fa fa-trash"
           :on-click #(delete-ix valor kw-ix)}]])

;; Video #3: Computed Properties => calcula o novo dominio, i.e. chama a função generate 
;; sempre que os arrays prefixes e sufixes forem alterados
;; generate() {
;; foreach prefix (foreach sufix) this.domains = this.prefix + this.sufix
;; }

(defn add-ix
  [val ix]
  (swap! data assoc ix (into [@val] (ix @data))))

(defn ix-input
  [valor placeholder kw-ix]
  [:div.input-group 
   [:input {:class "form-control"
            :type "text"
            :placeholder placeholder
            :value @valor
            :on-change #(reset! valor (-> % .-target .-value))}]
   [:div.input-group-append
    [:button {:class "btn btn-info"
              :on-click #(add-ix valor kw-ix)}
     [:span {:class "fa fa-plus"}]]]])


(defn app
  []
  [:div 
   [header]
   [:div {:id "main"}
    [:div.container
     [:div.row
      [:div.col-md
       [:h5 "Prefixos "
        [:span {:class "badge badge-info"} (count (:prefixes @data))]]
       [:div.card
        [:div.card-body
         [:ul.list-group
          (for [prefix (:prefixes @data)]
            [:li.list-group-item 
             [:div.row 
              [:div.col-md prefix]
              [:div.col-md.text-right 
               [delete-button prefix :prefixes]]]])]
         [:br]
         (let [val (r/atom "")]
           [ix-input val "Digite o prefixo" :prefixes])]]]
      [:div.col-md
       [:h5 "Sufixos "
        [:span {:class "badge badge-info"} (count (:sufixes @data))]]
       [:div.card
        [:div.card-body
         [:ul.list-group
          (for [sufix (:sufixes @data)]
            [:li.list-group-item 
             [:div.row
              [:div.col-md sufix]
              [:div.col-md.text-right
               [delete-button sufix :sufixes]]]])]
         [:br]
         (let [val (r/atom "")]
           [ix-input val "Digite o sufixo" :sufixes])]]]]
     [:br]
     [:h5 "Domínios "
      [:span {:class "badge badge-info"} (count (:domains @data))]]
     [:div.card
      [:div.card-body
       [:ul.list-group
        (for [domain (:domains @data)]
          [:li.list-group-item domain])]]]]
    ]])

(defn ^:dev/after-load start
  []
  (r/render [app]
            (.getElementById js/document "app")))

(defn ^:export init
  []
  (start))

