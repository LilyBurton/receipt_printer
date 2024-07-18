import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import scala.util.{Try, Failure, Success}
import Till._

class ReceiptPrinterSpec extends AnyWordSpec with Matchers {
  val coffeeConnectionCafe = new CafeDetails(
    "The Coffee Connection",
    "123 Lakeside Way",
    "16503600708",
    Map(
      "Cafe Latte" -> 4.75,
      "Flat White" -> 4.75,
      "Cappuccino" -> 3.85,
      "Single Espresso" -> 2.05,
      "Double Espresso" -> 3.75,
      "Americano" -> 3.75,
      "Cortado" -> 4.55,
      "Tea" -> 3.65,
      "Choc Mudcake" -> 6.40,
      "Choc Mousse" -> 8.20,
      "Affogato" -> 14.80,
      "Tiramisu" -> 11.40,
      "Blueberry Muffin" -> 4.05,
      "Chocolate Chip Muffin" -> 4.05,
      "Muffin Of The Day" -> 4.55
    )
  )

  "A ReceiptPrinter" should {
    "format a receipt" which {
      "contains the name of the cafe" in {
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe,
          Map("Cafe Latte" -> 1)
        )
        printer.receipt should include ("The Coffee Connection")
      }
     "format a reciept" which {
       "contains the address of the cafe" in {
         val printer = new ReceiptPrinter(
           coffeeConnectionCafe,
           Map("Cafe Latte" -> 1)
         )
         printer.receipt should include("123 Lakeside Way")
       }
     }
         "format a reciept" which {
           "contains the phone number of the cafe" in {
             val printer = new ReceiptPrinter(
               coffeeConnectionCafe,
               Map("Cafe Latte" -> 1)
             )
             printer.receipt should include("16503600708")
       }
     }
      "format a reciept" which {
        "contains an order from the menu" in {
          val printer = new ReceiptPrinter(
            coffeeConnectionCafe,
            Map("Cafe Latte" -> 1)
          )
          printer.receipt should include("Cafe Latte: 4.75")
          }
        }
      }

    "Till" should {
      "show a menu" which {
        "shows a list of drinks and food" in {

          val expectedMenu =
            """Cafe Latte: 4.75
              |Flat White: 4.75
              |Cappuccino: 3.85
              |Single Espresso: 2.05
              |Double Espresso: 3.75
              |Americano: 3.75
              |Cortado: 4.55
              |Tea: 3.65
              |Choc Mudcake: 6.40
              |Choc Mousse: 8.20
              |Affogato: 14.80
              |Tiramisu: 11.40
              |Blueberry Muffin: 4.05
              |Chocolate Chip Muffin: 4.05
              |Muffin Of The Day: 4.55""".stripMargin.split("\n").sorted.mkString("\n")

          val actualMenu = Till.showMenu(coffeeConnectionCafe).split("\n").sorted.mkString("\n")
          actualMenu shouldEqual expectedMenu

        }
      }
    "A Till" when {
      "add an item to an order" which {
        "Pick an item from the menu and add to the order" in {
          val initialOrder = Map("Choc Mudcake" -> 1)
          val updatedOrder = Till.addItem(coffeeConnectionCafe, initialOrder, "Choc Mudcake")
          val formattedOrder = Till.formatOrder(updatedOrder)
          formattedOrder shouldEqual ("Choc Mudcake: 2")
          }
        }
      "throw IllegalArgumentException if the item is not on menu" in {
        val actualOrder = Map("Choc Mudcake" -> 1)
        val exception = intercept[IllegalArgumentException] {
          Till.addItem(coffeeConnectionCafe, actualOrder, "Mocha")
        }
        exception.getMessage should include ("Mocha is not available on the menu")
      }
      }
    }
  }
}

