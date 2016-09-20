package com.gilt.handlebars.scala.helper

import com.gilt.handlebars.scala.binding.{Binding, BindingFactory}

class EachHelper[T] extends Helper[T] {
  def apply(binding: Binding[T], options: HelperOptions[T])(implicit contextFactory: BindingFactory[T]): String = {
    val arg0 = options.argument(0)
    if (arg0.isDictionary) {
      val col= arg0.asDictionaryCollection
      col.zipWithIndex.map {
        case ((key, value), idx) =>
          options.visit(value,
            Map(
	      "first" -> contextFactory.bindPrimitive(idx==0),
              "key" -> contextFactory.bindPrimitive(key),
              "index" -> contextFactory.bindPrimitive(idx),
	      "last" -> contextFactory.bindPrimitive( idx == col.size-1 )
           ))
      }.mkString
    } else if (arg0.isCollection) {
      val col= arg0.asCollection
      col.zipWithIndex.map {
        case (value, idx) =>
          options.visit(value,
            Map(
	      "first" -> contextFactory.bindPrimitive(idx==0),
              "index" -> contextFactory.bindPrimitive(idx),
	      "last" -> contextFactory.bindPrimitive( idx == col.size-1 )
           ))
      }.mkString
    }

    else {
//      warn("Could not iterate over argument for {{#each}}")
      ""
    }
  }
}
