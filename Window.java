package nyrain;

//Подключения необходимых библиотек
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Класс окна, в котором размещено игровое поле
public class Window extends JFrame
	{	
		private GamePanel gameP; // Закрытая переменная класса -- игровое поле
		private int difLevel; // Закрытая переменная класса -- сложность игры

		// Обработчик событий нажатий на клавиши
		private class ActiveKey implements KeyListener  
			{
		    	// Метод, который срабатывает при нажатии
	   	    	public void keyPressed(KeyEvent e)
	   	    	{
	   	    		// Получение кода нажатой клавиши
	   	    		int key_ = e.getKeyCode();
	   	    	
	   	    		// Выход из программы, если нажат - Esc 
	   	    		if (key_ == 27) {
	   	    			System.exit(0);	   	    		   	    	
	   	    		}
	   	    		else if (key_ == 37) // Если нажата стрелка влево
	   	    		{
	   	    			// Контроль перемещения влево за пределы окна
	   	    			if (gameP.x - 30 > -48) gameP.x -= 30;
	   	    			else gameP.x = 752;
	   	    		}	   	    	
	   	    		else if (key_ == 39) // Если нажата стрелка вправо
	   	    		{
	   	    			// Контроль перемещения вправо за пределы окна	   	    		
	   	    			if (gameP.x + 30 < 752) gameP.x += 30;
	   	    			else gameP.x = -48;
	   	    		}
	   	    	
	   	    }
	   	    public void keyReleased(KeyEvent e) {}
	   	    public void keyTyped(KeyEvent e) {}
	   }            	
	
		// Конструктор класса
		public Window(int difficulty)
		{
			// Помещение сложности, выбранной пользователем в переменную класса
			difLevel = difficulty;

			// Подключение обработчика события для клавиатуры к окну    	
			addKeyListener(new ActiveKey());
			
			// Установка активности окна
			setFocusable(true);
	
			// Задание размеров и положения окна
			setBounds(0, 0, 800, 600);
			
			// Задание заголовка окна
			setTitle("Игра: Новогодний дождь");
 
			// Создание объекта - игрового поля
			gameP = new GamePanel(difLevel);

			// Прикрепление (вложение) панели - игрового поля в окно
//			Container con = getContentPane();
			add(gameP);
   
			// Сделать окно видимым 
			setVisible(true);
		}
}
