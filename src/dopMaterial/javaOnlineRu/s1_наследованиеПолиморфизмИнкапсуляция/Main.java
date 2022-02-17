package dopMaterial.javaOnlineRu.s1_наследованиеПолиморфизмИнкапсуляция;

/**
 Наследование, полиморфизм, инкапсуляция

 Афоризм

 "Нахал!" – совсем не значит "Прекратите".

 Наталья Резник

 Объектно-ориентированное программирование ( ООП ) – подход к созданию программ, основанный на использовании классов и
 объектов, взаимодействующих между собой.

 Класс (java class) описывает устройство и поведение объектов. Устройство описывается через набор характеристик
 (свойств), а поведение – через набор доступных для объектов операций (методов). Классы можно создавать на основе уже
 имеющихся, добавляя или переопределяя свойства и методы.

 Классы представляют шаблоны, по которым строятся объекты. Объекты – это элементы программы, обладающие схожим
 набором характеристик и поведением (т.е это элементы, построенные на основе одного класса). Каждый объект имеет
 некоторое состояние, оно определяется значением всех его свойств. В одной программе могут существовать несколько
 классов, а объекты разных классов могут взаимодействовать между собой (через методы).

 Наследование, extends
 Наследование является неотъемлемой частью Java. При использовании наследования принимается во внимание, что новый
 класс, наследующий свойства базового (родительского) класса имеет все те свойства, которым обладает родитель.
 В коде используется операнд extends, после которого указывается имя базового класса. Тем самым открывается
 доступ ко всем полям и методам базового класса.

 Используя наследование, можно создать общий "java class", который определяет характеристики, общие для набора
 связанных элементов. Затем можно наследоваться от него и создать дополнительные классы, для которых определить
 дополнительные уникальные для них характеристики.

 Главный наследуемый класс в Java называют суперклассом super. Наследующий класс называют подклассом. Таким образом
 подкласс - это специализированная версия суперкласса, которая наследует все свойства суперкласса и добавляет свои
 собственные уникальные элементы.

 Рассмотрим пример описания java class'a студента Student, который имеет имя, фамилию, возраст, и номер группы.
 Класс студента будем создавать на основе super класса пользователя User, у которого уже определены имя, фамилия и возраст:

 public class User
 {
 int age;
 String firstName;
 String lastName;
 // Конструктор
 public User(int age, String firstName, String lastName)
 {
 this.age = age;
 this.firstName = firstName;
 this.lastName = lastName;
 }
 }
 Теперь создаем отдельный класс Student, наследующего свойства super класса. При наследовании класса
 необходимо также переопределить и конструкторы родительского класса :

 public class Student extends User
 {
 int group;

 // Конструктор
 public Student(int age, String firstName, String lastName)
 {
 super(age, firstName, lastName);
 }
 boolean isMyGroup(int g)
 {
 return g == group;
 }
 }
 Ключевое слово extends показывает, что мы наследуемся от класса User.

 Ключевое слово super
 В конструкторе класса Student мы вызываем конструктор родительского класса через оператор super, передавая ему
 весь необходимой набор параметров. В Java ключевое слово super обозначает суперкласс, т.е. класс, производным от
 которого является текущий класс. Ключевое слово super можно использовать для вызова конструктора суперкласса и для
 обращения к члену суперкласса, скрытому членом подкласса.

 Рассмотрим как происходит наследование с точки зрения создания объекта :

 Student student = new Student(18, "Киса", "Воробьянинов", 221);
 Сначала открывается конструктор класса Student, после этого вызывается конструктор суперкласса User,
 а затем выполняются оставшиеся операции в конструкторе Student. Такая последовательность действий вполне логична и
 позволяет создавать более сложные объекты на основе более простых.

 У суперкласса могут быть несколько перегруженных версий конструкторов, поэтому можно вызывать метод super()
 с разными параметрами. Программа выполнит тот конструктор, который соответствует указанным аргументам.

 Вторая форма ключевого слова super действует подобно ключевому слову this, только при этом мы всегда ссылаемся
 на суперкласс подкласса, в котором она использована. Общая форма имеет следующий вид:

 super.член
 Здесь член может быть методом либо переменной экземпляра. Подобная форма подходит в тех случаях, когда имена членов
 подкласса скрывают члены суперкласса с такими же именами.

 class A
 {
 int i;
 }

 // наследуемся от класса A
 class B extends A
 {
 int i; // имя переменной совпадает и скрывает переменную i в классе A

 B(int a, int b)
 {
 super.i = a; // обращаемся к переменной i из класса A
 i = b;       // обращаемся к переменной i из класса B
 }

 void show()
 {
 System.out.println("i из суперкласса равно " + super.i);
 System.out.println("i в подклассе равно " + i);
 }
 }

 class MainActivity
 {
 B subClass = new B(1, 2);
 subClass.show();
 }
 В результате в консоли мы должны увидеть :

 i из суперкласса равно 1
 i в подклассе равно 2

 Переопределение методов, Override
 Если в иерархии классов имя и сигнатура типа метода подкласса совпадает с атрибутами метода суперкласса, то метод
 подкласса переопределяет метод суперкласса. Когда переопределённый метод вызывается из своего подкласса, он всегда
 будет ссылаться на версию этого метода, определённую подклассом. А версия метода из суперкласса будет скрыта.

 Если нужно получить доступ к версии переопределённого метода, определённого в суперклассе, то необходимо использовать
 ключевое слово super.

 Не путайте переопределение с перегрузкой. Переопределение метода выполняется только в том случае, если имена и
 сигнатуры типов двух методов идентичны. В противном случае два метода являются просто перегруженными.

 В Java SE5 появилась анотация @Override;. Если необходимо переопределить метод, то используйте @Override,
 и компилятор выдаст сообщение об ошибке, если вместо переопределения будет случайно выполнена перегрузка.

 В Java можно наследоваться только от одного класса.
 Инкапсуляция
 В информатике инкапсуляцией (лат. en capsula) называется упаковка данных и/или функций в единый объект.

 Основой инкапсуляции в Java является класс. Инкапсуляция означает, что поля объекта недоступны его клиентам
 непосредственно - они скрываются от прямого доступа извне. Инкапсуляция предохраняет данные объекта
 от нежелательного доступа, позволяя объекту самому управлять доступом к своим данным.

 Модификаторы доступа
 При описании класса используются модификаторы доступа. Модификаторы доступа можно рассматривать
 как с позиции инкапсуляции так и наследования. Если рассматривать с позиции инкапсуляции,
 то модификаторы доступа позволяют ограничить нежелательный доступ к членам класса извне.

 Модификатор доступа	Область действия
 public	Без ограничений
 private	Только из данного класса
 protected	Из данного класса и его потомков
 Без модификатора	Для всех классов данного пакета
 Открытые члены класса составляют внешнюю функциональность, которая доступна другим классам. Закрытыми (private)
 обычно объявляются независимые от внешнего функционала члены, а также вспомогательные методы, которые являются лишь
 деталями реализации и неуниверсальны по своей сути. Благодаря сокрытию реализации класса можно менять внутреннюю
 логику отдельного класса, не меняя код остальных компонентов системы.

 Желательно использовать доступ к свойствам класса только через его методы (принцип bean классов, "POJO"),
 который позволяет валидировать значения полей, так как прямое обращение к свойствам отслеживать крайне сложно,
 а значит им могут присваиваться некорректные значения на этапе выполнения программы. Такой принцип относится к
 управлению инкапсулированными данными и позволяет быстро изменить способ хранения данных. Если данные станут
 храниться не в памяти, а в файлах или базе данных, то потребуется изменить лишь ряд методов одного класса,
 а не вводить эту функциональность во все части системы.

 Программный код, написанный с использованием принципа инкапсуляции легче отлаживать. Для того чтобы узнать,
 в какой момент времени и кто изменил свойство интересующего нас объекта, достаточно добавить вывод отладочной
 информации в тот метод объекта, посредством которого осуществляется доступ к свойству этого объекта.
 При использовании прямого доступа к свойствам объектов программисту бы пришлось добавлять вывод отладочной
 информации во все участки кода, где используется интересующий нас объект.

 Пример простого описания робота

 public class Robot
 {
 private double x      = 0; // Текущая координата X
 private double y      = 0; // Текущая координата Y
 private double course = 0; // Текущий курс (в градусах)

 public double getX() {
 return x;
 }
 public void setX(double x) {
 this.x = x;
 }

 public double getY() {
 return y;
 }
 public void setY(double y) {
 this.y = y;
 }

 public double getCourse() {
 return course;
 }

 // Определение курса
 public void setCourse(double course) {
 this.course = course;
 }

 // Передвижение на дистанцию distance
 public void forward(int distance) {
 // Обращение к полю объекта X
 x = x + distance * Math.cos(course / 180 * Math.PI);
 // Обращение к полю объекта Y
 y = y + distance * Math.sin(course / 180 * Math.PI);
 }

 // Печать координат робота
 public void printCoordinates() {
 System.out.println(x + "," + y);
 }
 }
 В представленном примере робота используются наборы методов, начинающие с set и get. Эту пару методов часто называют
 сеттер/геттер. Данные методы используются для доступа к полям объекта. Наименования метода заканчиваются
 наименованием поля, начинающееся с ПРОПИСНОЙ буквы.

 В методах set мы передаем значение через формальный параметр во внутрь процедуры. В коде процедуры мы присваиваем
 значение переменной объекта/класса с использованием ключевого слова this.

 ...
 this.course = course
 ...
 Использование ключевого слова this необходимо, т.к. наименование формального параметра совпадает с наименованием
 переменной объекта. Если бы наименования отличались бы, то можно было бы this не использавать.

 Полиморфизм, polymorphism
 Полиморфизм является одним из фундаментальных понятий в объектно-ориентированном программировании наряду с
 наследованием и инкапсуляцией. Слово полиморфизм греческого происхождения и означает "имеющий много форм".
 Чтобы понять, что означает полиморфизм применительно к объектно-ориентированному программированию, рассмотрим пример
 создания векторного графического редактора, в котором необходимо использовать ряд классов в виде набора графических
 примитивов - Square, Line, Circle, Triangle, и т.д. У каждого из этих классов необходимо определить метод draw для
 отображения соответствующего примитива на экране.

 Очевидно, придется написать некоторый код, который для изображения рисунка будет последовательно перебирать
 все примитивы, которые необходимо вывести на экран, и вызывать метод draw у каждого из них.

 Человек, незнакомый с полиморфизмом, вероятнее всего создаст несколько массивов: отдельный массив для каждого
 типа примитивов и напишет код, который последовательно переберет элементы из каждого массива и вызовет у каждого
 элемента метод draw. В результате получится примерно следующий код:

 // Определение массивов графических примитивов
 Square  [] s = new Square  [1000];
 Line    [] l = new Line    [1000];
 Circle  [] c = new Circle  [1000];
 Triangle[] t = new Triangle[1000];

 // Заполнение всех массивов соответствующими объектами
 . . .
 // Цикл с перебором всех ячеек массива.
 for (int i = 0; i < s.length; i++){
 // вызов метода draw() в случае, если ячейка не пустая.
 if (s[i] != null)
 s.draw();
 }
 for(int i = 0; i < l.length; i++){
 if (l[i] != null)
 l.draw();
 }
 for(int i = 0; i < c.length; i++){
 if (c[i] != null)
 c.draw();
 }
 for(int i = 0; i < t.length; i++){
 if (t[i] != null)
 t.draw();
 }
 Недостатком написанного выше кода является дублирование практически идентичного кода для отображения каждого
 типа примитивов. Также неудобно то, что при дальнейшей модернизации нашего графического редактора и добавлении
 возможности рисовать новые типы графических примитивов, например Text, Star и т.д., при таком подходе придется
 менять уже существующий код и добавлять в него определения новых массивов, а также обработку элементов,
 содержащихся в них.

 Используя полиморфизм, можно значительно упростить реализацию подобной функциональности. Прежде всего, создадим
 общий родительский класс Shape для всех наших классов.

 public class Shape
 {
 public void draw()
 {
 System.out.println("Заглушка");
 }
 }
 После этого мы создаем различные классы-наследники: Square (Квадрат), Line (Линия), Сircle (круг) и Triangle (Треугольник):

 public class Point extends Shape
 {
 public void draw() {
 System.out.println("Квадрат");
 }
 }

 public class Line extends Shape
 {
 public void draw() {
 System.out.println("Линия");
 }
 }

 public class Сircle extends Shape {
 public void draw() {
 System.out.println("Круг");
 }
 }

 public class Triangle extends Shape {
 public void draw() {
 System.out.println("Треугольник");
 }
 }
 В наследниках у нас переопределен метод draw. В результате получили иерархию классов, которая изображена на рисунке.


 Теперь проверим удивительную возможность полиморфизма:

 // Определение и инициализация массива
 Shape[] a = new Shape[] {new Shape(), new Triangle(), new Square(), new Сircle()};

 // Перебор в цикле элементов массива
 for(int i = 0; i < a.length; i++) {
 a[i].draw();
 }
 В консоль будут выведены следующие строки:

 Заглушка
 Треугольник
 Квадрат
 Круг

 Таким образом каждый класс-наследник вызвал именно свой метод draw, вместо того, чтобы вызвать метод draw из
 родительского класса Shape.

 Полиморфизм - положение теории типов, согласно которому имена (например, переменных) могут обозначать объекты
 разных, но имеющих общего родителя, классов. Следовательно, любой объект, обозначаемый полиморфным именем,
 может посвоему реагировать на некий общий набор операций.

 Перегрузка метода, overload
 В процедурном программировании тоже существует понятие полиморфизма, которое отличается от рассмотренного
 механизма в ООП. Процедурный полиморфизм предполагает возможность создания нескольких процедур или функций
 с одинаковым именем, но разными количеством или типами передаваемых параметров. Такие одноименные функции
 называются перегруженными, а само явление - перегрузкой (overload). Перегрузка функций существует и в ООП и
 называется перегрузкой методов. Примером использования перегрузки методов в языке Java может служить класс PrintWriter,
 который используется в частности для вывода сообщений на консоль. Этот класс имеет множество методов println,
 которые различаются типами и/или количеством входных параметров. Вот лишь несколько из них:

 void println()            // переход на новую строку
 void println(boolean x)   // выводит значение булевской переменной (true или false)
 void println(String x)    // выводит строку - значение текстового параметра
 */
public class Main {
}
