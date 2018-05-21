// !LANGUAGE: +InlineClasses
// !SKIP_METADATA_VERSION_CHECK
// WITH_UNSIGNED
// WITH_REFLECT

@file:Suppress("INVISIBLE_MEMBER")

annotation class AnnoUB(val ub0: UByte, val ub1: UByte)
annotation class AnnoUS(val us0: UShort, val us1: UShort)
annotation class AnnoUI(val ui0: UInt, val ui1: UInt, val ui2: UInt, val ui3: UInt)
annotation class AnnoUL(val ul0: ULong, val ul1: ULong)

const val ub0 = UByte(1)
const val us0 = UShort(2)
const val ul0 = ULong(3)

const val ui0 = UInt(-1)
const val ui1 = UInt(0)
const val ui2 = UInt(40 + 2)

object Foo {
    @AnnoUB(UByte(1), ub0)
    fun f0() {}

    @AnnoUS(UShort(2 + 5), us0)
    fun f1() {}

    @AnnoUI(ui0, ui1, ui2, UInt(100))
    fun f2() {}

    @AnnoUL(ul0, ULong(5))
    fun f3() {}
}

fun box(): String {
    if (ub0.toByte() != 1.toByte()) return "fail"
    if (us0.toShort() != 2.toShort()) return "fail"
    if (ul0.toLong() != 3L) return "fail"
    if ((ui0 + ui1 + ui2).toInt() != 41) return "fail"

    val anno = Foo::f2.annotations.first() as AnnoUI
    if (anno.ui0 != UInt.MAX_VALUE) return "fail"

    return "OK"
}