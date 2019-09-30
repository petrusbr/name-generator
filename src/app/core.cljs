(ns app.core
  (:require [reagent.core :as r]))

(def data {:prefixes ["Air" "Jet" "Flight"]
           :sufixes ["Hub" "Station" "Mart"]
           :domains ["Airhub" "AirStation" "AirMart" "JetHub" 
                     "JetStation" "JetMart" "FlightHub" "FlighStation" 
                     "FlightMart"]})


(def info (r/atom "Pedro "))

(defn greeting [message]
  [:h1 (str message " playboy!")])

(defn app
  []
  [:div.container 
   [:div.row 
    [:div.col-md 
     [:h5 @info 
      [:span {:class "badge badge-info"} (count data)]]
     [:div.card 
      [:div.card-body 
       [:ul.list-group 
        (for [prefix (:prefixes data)]
          [:li.list-group-item prefix])]]]]]])

(defn ^:dev/after-load start
  []
  (r/render [app]
            (.getElementById js/document "app")))

(defn ^:export init
  []
  (start))

