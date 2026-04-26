package org.example.lab13

import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.control.TextField
import kotlin.math.*
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.stage.Modality
import javafx.stage.Stage


class HelloController {
    @FXML
    lateinit var startField: TextField
    lateinit var endField: TextField
    lateinit var stepField: TextField
    lateinit var table: TableView<Point>
    lateinit var xCol: TableColumn<Point, Double>
    lateinit var yCol: TableColumn<Point, Double>
    lateinit var xField: TextField
    lateinit var nField: TextField
    lateinit var epsField: TextField
    lateinit var resultArea: Label


    fun close (event: ActionEvent){
        javafx.application.Platform.exit()
    }

    fun onAboutMenuClick() {
        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("about_program.fxml"))
        val root = fxmlLoader.load<Parent>()

        val stage = Stage()
        stage.title = "Про програму"


        stage.initModality(Modality.APPLICATION_MODAL)

        stage.initOwner(table.scene.window)

        stage.scene = Scene(root)
        stage.showAndWait()
    }




    fun tabulation() {
        val start = startField.text.toDouble()
        val end = endField.text.toDouble()
        val step = stepField.text.toDouble()

        val points:List<Point> = tabulateFunction(start, end, step)
        table.items = FXCollections.observableList(points)

    }
    fun tabulateFunction(start: Double, end: Double, step: Double): List<Point> {
        val points = mutableListOf<Point>()

        val n = ((end - start) / step).toInt()

        for (i in 0..n) {
            val x = start + i * step
            val d = 1.0 - x * x

            if (d > 0) {
                val y = f(x)
                points.add(Point(x.toString(), y.toString()))
            } else {
                points.add(Point(x.toString(), "не існує"))
            }
        }
        return points
    }
    fun f(x: Double): Double {
        return exp(2.0) / sqrt(1.0 - x * x)}

    fun initialize() {
        xCol.cellValueFactory = PropertyValueFactory("x")
        yCol.cellValueFactory = PropertyValueFactory("y")
    }




    fun calculate(){
        val x = xField.text.toDouble()
        val n = nField.text.toInt()
        val eps = epsField.text.toDouble()
        if (x >= 1.0 || x <= -1.0) {
            resultArea.text = "Error: x must be in range (-1, 1)"
            return
        }
        val sumN = calculateSumN(n, x)
        val (sumE, count) = calculateSumE(n, x, eps)
        val (exactValue, difference) = compareValues(x, sumN)

        resultArea.text = """
            Сума n членів: ${"%.5f".format(sumN)}
            Сума (>eps): ${"%.5f".format(sumE)}
            Кількість членів: $count
            Точне значення: ${"%.5f".format(exactValue)}
            Похибка: ${"%.5e".format(difference)}
        """.trimIndent()
    }

    private fun calculateSumN(n: Int, x: Double): Double {
        var sum = 0.0
        for (i in 1..n) {
            sum += -(x.pow(i) / i)
        }
        return sum
    }

    private fun calculateSumE(n: Int, x: Double, eps: Double): Pair<Double, Int> {
        var sum = 0.0
        var count = 0
        for (i in 1..n) {
            val term = -(x.pow(i) / i)
            if (abs(term) > eps) {
                sum += term
                count++
            }
        }
        return Pair(sum, count)
    }

    private fun compareValues(x: Double, sumN: Double): Pair<Double, Double> {
        val exactValue = ln(1 - x)
        val difference = abs(exactValue - sumN)
        return Pair(exactValue, difference)
    }
}

