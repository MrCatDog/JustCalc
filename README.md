# JustCalc
My first Android app project. Attempt to make simpler version of "Calculator ++" from Play Market witch written by Sergey Solovyev.   
----------------------------------------
Целью проекта было создание калькулятора "так же, как вот там", где за образец взят "Калькулятор++" из Google play.
Реализован пересчёт результата "на ходу", после каждого изменения в выражении, а если это невозможно - сохранение предыдущего со сменой его цвета.
Выполнение операций вычисления вынесено в отдельный поток, что позволяет UI оставатся отзывчивым к действиям пользователя.
Некоторые кнопки запрограммированы различть свайпы вверх и вниз, предоставляя дополнительные возможности ввода. 
Весь программный код написан с учётом последующего, возможного расширения.
Итоговый APK ("app-debug.apk") для тестирования включён в проект.
Приложение проверялось на виртуальном устройстве Nexus 5X (API 29) и реальном устройстве Sony XZ1 Compact (API 28).
