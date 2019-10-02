(ns app.core
  (:require [reagent.core :as r]))

(def data (r/atom {:prefixes ["Air" "Jet" "Flight"]
                   :sufixes ["Hub" "Station" "Mart"]
                   :domains ["Airhub" "AirStation" "AirMart" "JetHub"
                             "JetStation" "JetMart" "FlightHub" "FlighStation"
                             "FlightMart"]}))
;; => #'app.core/data


(defn header
  [] 
  [:div {:id "slogan" :class "text-center"} 
   [:h1 "Name Generator"]
   [:h6.text-secondary "Gerador de nomes utilizando ClojureScript e Clojure"]])

(defn ix-input 
  [valor placeholder]
  [:input {:class "form-control" 
           :type "text"
           :placeholder placeholder
           :value @valor
           :on-change #(reset! valor (-> % .-target .-value))}])

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
            [:li.list-group-item prefix])]
         [:br]
         (let [val (r/atom "")]
           [:div.input-group
            [ix-input val "Digite o prefixo"]
            [:div.input-group-append
             [:button {:class "btn btn-info"
                       :on-click #(js/console.log @val)}
              [:span {:class "fa fa-plus"}]]]]
           )
         
         ]]]
      [:div.col-md
       [:h5 "Sufixos "
        [:span {:class "badge badge-info"} (count (:sufixes @data))]]
       [:div.card
        [:div.card-body
         [:ul.list-group
          (for [sufix (:sufixes @data)]
            [:li.list-group-item sufix])]
         [:br]
         [:div.input-group 
          [:input {:class "form-control" :type "text"
                   :placeholder "Digite o sufixo"}]
          [:div.input-group-append
           [:button {:class "btn btn-info"}
            [:span {:class "fa fa-plus"}]]]]]]]]
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

