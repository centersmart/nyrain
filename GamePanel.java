package nyrain;

//Подключение необходимых библиотек
import javax.imageio.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Класс панели, которая является игровым полем
class GamePanel extends JPanel
{
	  private Image hat_img; // Закрытая Переменная класса, в которую загружается шапка
	  private Image bg_img; // Закрытая Переменная класса, в которую загружается фон
	  private Image end_game; // Изображение Окончания игры
	  public int x = 400; // Открытая Переменная класса, в которую загружается шапка
	  private int difLevel; // Переменная сложности игры
	  private Gift [] gameGift; // Массив подарков
	  public Timer timerRespawn, timerDraw; // Два таймера: первый для 
	  	  
	   // Конструктор класса 
	   public GamePanel(int difLevel)
	   {		   
		   this.difLevel = difLevel;			  
		   // Загрузка изображения шапки из файла
		   try
		   {
		       hat_img = ImageIO.read(new File("c:\\hat.png"));
		   }
		   catch(IOException ex) {
			   JOptionPane.showMessageDialog(null, "Файл не найден", "Ошибка чтения файла", 1);
		   }
		   
		   // Загрузка изображения фона из файла
		   try
		   {
		       bg_img = ImageIO.read(new File("c:\\bg.png"));
		   }
		   catch(IOException ex) {
			   JOptionPane.showMessageDialog(null, "Файл не найден", "Ошибка чтения файла", 1);
		   }
		   
	           // Загрузка изображения Окончания игры
		   try
		   {
		     end_game = ImageIO.read(new File("c:\\end_game.png"));
		   }
		   catch(IOException ex) {
			   JOptionPane.showMessageDialog(null, "Файл не найден", "Ошибка чтения файла", 2);
		   }	

		   //  Загрузка семи изображений подарков
		   gameGift = new Gift[7];
		   for (int i = 0; i < 7; i++)
		   {
	           try
	           {
	        	   gameGift[i] =
	        			   new Gift(ImageIO.read(new File("c:\\p" + i + ".png")));
	           }
	           catch (IOException ex) {
	        	   JOptionPane.showMessageDialog(null, "Файл не найден", "Ошибка чтения файла", 1);
	           }
		   }


		   // Создание таймера, который будет раз в три секунды проверять и добавлять подарки на игровое поле 		   
		   timerRespawn = new Timer(3000, new ActionListener() {				
			   		public void actionPerformed(ActionEvent e) {
//			   		System.out.println("I'm spawn");
			   		giftSpawner(); // Метод для проверки и добавление подарков на игровое поле
			   		}
				});		    
		   timerRespawn.start(); // Запуск таймера	   		   

		   // Создание таймера, который будет перерисовывать игровое поле 20 раз в секунду 	   
		   timerDraw = new Timer(50, new ActionListener() {				
					public void actionPerformed(ActionEvent e) {
		            repaint(); // Запуск метода перерисовки поля (public void paintComponent(Graphics gr))
					}
				});		    
		   timerDraw.start(); // Запуск таймера для перерисовки
		    			    		    		    		    		    
	   }
	   	   
	   // Метод, который отрисовывает графические объекты на панели
	   public void paintComponent(Graphics gr)
	   {
		   // Выполнить отрисовку сначала самого окна
//		   super.paintComponent(gr);
		   gr.drawImage(bg_img, 0, 0, null); // Рисование фона 
		   gr.drawImage(hat_img, x, 465, null); // Рисование шапки

		   // Цикл, который отображает подарки на игровом поле и проверяет пропущенные подарки
		   for (int i = 0; i < 7; i++)
		   	{
			   gameGift[i].draw(gr); // Отображение подарка
		   		
			   if (gameGift[i].isActive == true) {
//				   System.out.println(gameGift[i].y);
				   if (gameGift[i].y + gameGift[i].img.getHeight(null) >= 460) {
					   
					   if (Math.abs(gameGift[i].x - x) > 10) {

						   gr.drawImage(end_game, 300, 300, null);
						   timerDraw.stop();
						   timerRespawn.stop();
						   break;
					   } else gameGift[i].isActive = false;
				   }
			   }
		   	}
	   }


      // Метод для проверки и добавление подарков на игровое поле
	   private void giftSpawner()
	   		{
		   		int activeGifts = 0; // Переменная для подсчета подарков на игровом поле
//		   		System.out.println(difLevel);
		   		for (int i = 0; i < 7; i++) // Цикл перебора всех подарков массива
		   			{
		   				if (gameGift[i].isActive == false) // Если подарок не на игровом поле
		   					{
//		   						System.out.println(gameGift[i].isActive);
		   						if (activeGifts < difLevel) // Если текущее количество менее номера сложности (от 1 до 7)
		   							{
//		   								System.out.println("before spawn");
		   								gameGift[i].spawn(); // Активизация подарка на игровом поле, вывод его сверху игрового поля
		   								break; // Прерывание цикла
		   							}
		   					} else activeGifts++; // Если подарок на игровом поле
		   			}

	   		}	   
}
