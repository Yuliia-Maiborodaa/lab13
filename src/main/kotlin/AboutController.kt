package org.example.lab13

import javafx.fxml.FXML
import javafx.scene.control.TextArea
import javafx.stage.Stage
import java.io.File

class AboutController {

    @FXML
    private lateinit var infoArea: TextArea

    @FXML
    fun initialize() {
        infoArea.text = """
            Лабораторна робота №13
            Тема: Створення програми з графічним інтерфейсом. JavaFX
            Розробник: Юлія Майборода
            Група: 1141
            
            Завдання:
            Завдання 13.1
            Скласти програму табулювання функції f(x) на відрізку [a; b] з кроком h.
            Значення a, b, h вводити з клавіатури. Обов’язково врахувати область визначення
            функції.
            Завдання 13.2
            Для заданих x, n, eps, що вводяться з клавіатури:
            a. Обчислити n доданків згідно з варіантом;
            b. Обчислити суму тих доданків, які за абсолютним значенням більше eps.
            (Завдання виконати для двох різних eps, які відрізняються на порядок, для кожного
            випадку обчислити кількість доданків).
            c. Порівняти результати з «точним» значенням відповідної функції (сума
            визначає наближене значення) для x Є (-R,R).
        """.trimIndent()
    }

    @FXML
    fun onSaveToFile() {
        val file = File("about_program.txt")
        file.writeText(infoArea.text)
    }


    @FXML
    fun closeAbout() {
        val stage = infoArea.scene.window as Stage
        stage.close()
    }
}