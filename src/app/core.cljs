(ns app.core
  (:require [reagent.core :as r]))

(def data {:prefixes ["Air" "Jet" "Flight"]
           :sufixes ["Hub" "Station" "Mart"]
           :domains ["Airhub" "AirStation" "AirMart" "JetHub" 
                     "JetStation" "JetMart" "FlightHub" "FlighStation" 
                     "FlightMart"]})

(defn header
  [] 
  [:div {:id "slogan" :class "text-center"} 
   [:h1 "Name Generator"]
   [:h6.text-secondary "Gerador de nomes utilizando ClojureScript e Clojure"]])

(defn app
  []
  [:div 
   [header]
   [:div {:id "main"}
    [:div.container
     [:div.row
      [:div.col-md
       [:h5 "Prefixos "
        [:span {:class "badge badge-info"} (count (:prefixes data))]]
       [:div.card
        [:div.card-body
         [:ul.list-group
          (for [prefix (:prefixes data)]
            [:li.list-group-item prefix])]]
        [:input {:class "form-control" :type "text"
                 :placeholder "Digite o prefixo"}]]]
      [:div.col-md
       [:h5 "Sufixos "
        [:span {:class "badge badge-info"} (count (:sufixes data))]]
       [:div.card
        [:div.card-body
         [:ul.list-group
          (for [sufix (:sufixes data)]
            [:li.list-group-item sufix])]]
        [:input {:class "form-control" :type "text"
                 :placeholder "Digite o sufixo"}]]]]
     [:br]
     [:h5 "Domínios "
      [:span {:class "badge badge-info"} (count (:domains data))]]
     [:div.card
      [:div.card-body
       [:ul.list-group
        (for [domain (:domains data)]
          [:li.list-group-item domain])]]]]
    ]])

(defn ^:dev/after-load start
  []
  (r/render [app]
            (.getElementById js/document "app")))

(defn ^:export init
  []
  (start))

