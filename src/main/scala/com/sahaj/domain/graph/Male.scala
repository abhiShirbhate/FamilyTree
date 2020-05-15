package com.sahaj.domain.graph

class Male(override val name:String, parent: Option[Family]) extends Individual(name, parent) {

  def marry(bride:Female):Family = Family(this, bride)
}
