package com.ryu.androidstorage.basic

class LamdaNull {
}

typealias MyFunType = (Int, Int) -> Boolean


fun main(){
    val some = {no: Int -> println((no))}
    some(10)

    val some2: (Int) -> Unit = { println(it)} // 매개변수를 int로 선언했으므로 it이 가리키는 데이터가 int 타입임을 알 수 있음
    some2(10)

    val some3 = {no1: Int, no2: Int ->
        println("lamda")
        no1*no2
    }
    println("${some3(10, 20)}")

    val someFun: MyFunType = { no1: Int, no2: Int -> no1 > no2} // no1 no2 타입 생략 가능
    println(someFun(10,20))


    //널 안정성으로 고려해야 함
    var data: String? = null
    val length = if (data == null){
        0
    } else {
        data.length
    }
    println("$length")
    println("${data?.length ?: 0}") //data가 null이면 0을 반환 아니면 data.length, ?: 는 null일 경우 default 값을 주기 위해 사용
}