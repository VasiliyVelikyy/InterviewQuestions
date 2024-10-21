## Методы класса Object

* public String toString()
  Возвращает строковое представление объекта.

* public native int hashCode()
* public boolean equals(Object obj)
  Пара методов, которые используются для сравнения объектов.

* public final native Class getClass()
  Возвращает специальный объект, который описывает текущий класс.

* public final native void notify()
* public final native void notifyAll()
* public final native void wait(long timeout)
* public final void wait(long timeout, intnanos)
* public final void wait()
  Методы для контроля доступа к объекту из различных нитей. Управление синхронизацией нитей.

* protected void finalize()
  Метод позволяет «освободить» родные не-Java ресурсы: закрыть файлы, потоки и т.д.

* protected native Object clone()
  Метод позволяет клонировать объект: создает дубликат объекта