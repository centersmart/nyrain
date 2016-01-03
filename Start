package nyrain;
//Подключение необходимых библиотек
import javax.swing.*;

//Главный класс игры
public class Start {

	// Главный метод, который запускает игру
	public static void main(String[] args) {
		
		   // Вызов диалогового окна для ввода сложности игры
		   String input = JOptionPane.showInputDialog
              (null, "Введите сложность игры от 1 до 7:", "Сложность игры", 1);
		   
		   // Помещение результата выбора в переменную целого типа
		   int difficulty = Integer.parseInt(input);
		   
		   // Проверка, что введена цифра от 1 до 7
		   if (difficulty >= 1 && difficulty <= 7)
		   {
			   // Создание окна, в котором находится игровое поле
			   Window gameWindow = new Window(difficulty);
		   }
	}
}
