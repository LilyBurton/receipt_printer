// src/main/scala/ReceiptPrinter.scala
class CafeDetails (
                    val shopName: String,
                    val address: String,
                    val phone: String,
                    val prices: Map[String, Double]
                  )

class ReceiptPrinter(val cafe: CafeDetails, var order: Map[String, Int] = Map()) {


  def receipt: String = {
    val cafeDetail = List(cafe.shopName, cafe.address, cafe.phone).mkString(", ")
    val orderDetails = formatOrder()
    s"""
       |$cafeDetail
       |
       |Order Details:
       |$orderDetails
    """.stripMargin
  }

  private def formatOrder(): String = {
    val orderDetails = order.map { case (item, quantity) =>
    val price = cafe.prices.getOrElse(item, 0.0) * quantity
    s"$item: $price"
    }.mkString("\n")
    orderDetails
  }
}

//class Till(val detail: ReceiptPrinter) {
//
//}