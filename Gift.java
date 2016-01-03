package nyrain;

//Подключения необходимых библиотек
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Класс для подарка, падающего сверху
class Gift {
	
	public Image img; // Изображение подарка
	public int x, y; // Положение подарка на игровом поле, в пикселях, x - отступ слева, y - отступ сверху
	public Boolean isActive; // Переменная логического типа, показывающая активность подарка, есть он игровом поле или нет
	Timer timerUpdate; // Таймер, отвечающий за движение подарка вниз

	// Конструктор класса
	public Gift(Image img)
		{
		// Создание и настройка таймера, отвечающего за движение подарка вниз
			timerUpdate = new Timer(500, new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				isActive = true;
				goDown(); // Метод, осуществляющий движение подарка вниз
				}
	    });		
			this.img = img; // Передача изображения из круглых скобок Конструктора класса в переменную класса
			isActive = false; // Изначально делаем подарок неактивным, отсутствующим на игровом поле
//			timerUpdate.start();
}

	// Метод, выполняющий активизацию подарка на игровом поле, вывод его сверху игрового поля
	public void spawn()
	{
		timerUpdate.setDelay(300); // Установка временной задержки для таймера
		timerUpdate.start(); // Запуск таймера
		y = 0; // Отступ сверху в пикселях
		x = (int) (Math.random() * 700); // Отступ слева в пикселях, получаем случайным образом от 0 до 700
		isActive = true; // 
}

	// Метод, осуществляющий движение подарка вниз 
	public void goDown()
		{
//			System.out.println("I'm down");
			if (isActive == true) // Если подарок активен на игровом поле
				{
					y = y + 6; // Увеличение отступа сверху на 6 пикселей
				}
			
			if ((y + img.getHeight(null)) >= 470) // Если подарок достиг нижней границы
				{
					isActive = false;
					timerUpdate.stop(); // Остановка таймера
				}
			}

		// Метод, выполняющий отрисовку подарка на игровом поле, если он активен
		public void draw(Graphics gr)
			{
				if (isActive == true)
					{
						gr.drawImage(img, x, y, null); // Рисование изображения
					}    	
			}
}
