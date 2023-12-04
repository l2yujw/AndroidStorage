package com.ryu.difference_check

class ClassCheck (var name: String){//주 생성자의 매개변수는 var val을 통해 값을 받는 동시에 클래스의 멤버 변수로 활용 가능하다
    var count = 0

    init {
        this.name = name
    }//우선순위

    constructor(name: String, count: Int): this(name){ //클래스 내에 주 생성자가 있다면 this를 통해 주 생성자를 호출해야 함
        this.name = name
        this.count = count
    } //자바의 생성자 생각 , 생성자가 실행시 init 대신 생성자 구문 적용
}

class ClassInit(name: String, count: Int){
    var name: String
    var count: Int
    init{
        this.name = name
        this.count = count
        println("$name $count")
    }
}

open class Super(name: String){// 상속할 수 있게 선언하기 위해 open 사용
}
class Sub(name: String): Super(name){
}
class SubCon: Super{
    constructor(name: String): super(name){
    }
}


open class Super2 {
    open var someData = 10
    open fun someFun(){
        println("i am super class function : $someData")
    }
}

class Sub2: Super2(){
    override var someData = 20
    override fun someFun() {
        println("$someData")
    }
//    protected var 해당 클래스 내부와 그 클래스를 상속받는 하위클래스에서 접근 가능
//    private var 해당 클래스 내부에서만 접근 가능
}


class NonDataClass(val name: String, val email: String, val age: Int){
}

data class DataClass(val name: String, val email: String, val age: Int){
    lateinit var address:String
    constructor(name: String, email: String, age: Int, address: String): this(name, email, age){
        this.address = address
    }
}



open class SuperObj{
    open var data = 10
    open fun some(){
        println("$data")
    }
}

val obj = object: SuperObj() {
    override var data = 10
    override fun some() {
        println("$data")
    }
}//코틀린에서 object class는 익명 클래스를 만들기 위해 사용


class MyClass{
    companion object{
        var data = 10
        fun some(){
            println(data)
        }
    }//클래스 이름으로 멤버에 접근할 수 있게 하려면 companion object로 묶어서 접근이 가능하게 해야함
}

fun main(){
    val obj1 = DataClass("ryu", "a@a.com", 10, "seoul")
    val obj2 = DataClass("ryu", "a@a.com", 10, "busan")
    println("${obj1.equals(obj2)}")
    //data class는 객체의 데이터를 비교함, 데이터 내부의 값은 같기 때문에 true가 나옴. 단, 주 생성자에 선언한 멤버 변수의 데이터만 비교 대상으로 삼음
    //그냥 class로 선언할 경우 서로 다른 객체이므로 false가 나옴


    val non = NonDataClass("ryu", "a@a.com", 10)
    val data = DataClass("ryu", "a@a.com", 10)
    println("${non.toString()}")
    println("${data.toString()}")


    obj.data = 20
    obj.some()


    MyClass.data=20
    MyClass.some()
}