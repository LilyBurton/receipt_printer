object Till {

  def showMenu(cafe: CafeDetails): String = {
    cafe.prices.map { case (item, price) =>
      f"$item: $price%.2f"}
  }.mkString("\n")

  def addItem(cafe: CafeDetails, order: Map[String, Int], item: String): Map[String, Int] = {
    if (cafe.prices.contains(item)) {
      val currentQuantity = order.getOrElse(item, 0)
      order + (item -> (currentQuantity + 1))
    } else {
      throw new IllegalArgumentException(s"$item is not available on the menu")
      }
    }
    def formatOrder(order: Map[String, Int]): String = {
      order.map { case (item, quantity) => s"$item: $quantity"}.mkString("\n")
    }
  }

