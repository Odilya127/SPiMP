#Напишите функцию копирующую из входного канала в выходной только уникальные значения
#(которые ранее не встречались). В случае прихода из входного канала сообщения 
#:reset - информация о “накопленных” значениях - сбрасывается.
from queue import Queue
import threading
def process_unique_values(input_queue, output_queue):
    unique_values_set = set()
    while True:
        message = input_queue.get()
        if message.lower() == ":reset":
            unique_values_set.clear()
            print("Очистка уникальных значений.")
        else:
            if message not in unique_values_set:
                unique_values_set.add(message)
                output_queue.put(message)
                print("Добавлено уникальное значение:", message)
            else:
                print("Значение уже существует в уникальных значениях.")
input_queue = Queue()
output_queue = Queue()
thread = threading.Thread(target=process_unique_values, args=(input_queue, output_queue))
thread.start()
while True:
    user_input = input("Введите значение или :reset для сброса: ")
    input_queue.put(user_input)
