package com.ryu.androidstorage.basic

class Basic {

        /*
                var 가변
                val 초깃값 할당 이후 변경 불가
                lateinit 이후에 초깃값을 할당할 것
                : Any 모든 타입의 데이터 할당 가능   : Unit 객체만 대입 가능    : Nothing 눌이나 예외만 반환
                타입 뒤에 ? 추가시 널 허용
        
                val data1: Array<Int> = Array(3, { 0 }) 사이즈, 초기화값
                val data1 = arrayOf<Int>(10, 20, 30) 선언과 동시에 초기화

                var list = listOf<Int>(10, 20, 30) //순서가 있는 데이터 집합, 데이터의 중복을 허용, 불변
                var mutableList = mutableListOf<Int>(10, 20, 30)//가변
                var map = mapOf<String, String>(Pair("one", "hello"), "two" to "world") //Pair 또는 to를 통해서 대입 가능
                println(map["one"])

                when (data){
                    10 -> println("10")
                is String -> println("String")
                in 1..10 -> println("1..0")
                    else -> println("data")
                }//switch case

                for(i in 1..10 step 증가량) // in 1..10 1~10까지 1씩 증가    1 until 10 1부터 9까지 1씩 증가    2..10 step 2 2부터 2씩 증가    10 downTo 1 감소
        */
}