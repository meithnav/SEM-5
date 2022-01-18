# from datetime import date
# Date = date(2021,1,12)
# print("Date: ", Date)

# from datetime import date
# today = date.today()
# print("Today's date is", today)


# from datetime import date
# today = date.today()
# print("Current year:", today.year)
# print("Current month:", today.month)
# print("Current day:", today.day)

# from datetime import datetime
# date_time = datetime.fromtimestamp(1887639708)
# print("Datetime from timestamp:", date_time)


# from datetime import date
# today = date.today()
# Str = date.isoformat(today)
# print("String Representation", Str)
# print(type(Str))

# from datetime import time
# my_time = time(10, 22, 45)
# print("Entered time", my_time)
# my_time = time(minute=25)
# print("Time with one argument", my_time)
# my_time = time()
# print("Time without argument", my_time)


# from datetime import time
# Time = time(12, 15, 47)
# print("hour =", Time.hour)
# print("minute =", Time.minute)
# print("second =", Time.second)
# print("microsecond =", Time.microsecond)

# from datetime import datetime, timedelta
# ini_time_for_now = datetime.now()
# print("initial_date", str(ini_time_for_now))
# future_date_after_200d = ini_time_for_now + timedelta(days=200)

# future_date_after_10d = ini_time_for_now + timedelta(days=10)

# print('future_date_after_200d:', str(future_date_after_200d))
# print('future_date_after_10d:', str(future_date_after_10d))

from datetime import datetime, timedelta
ini_time_for_now = datetime.now()
print("initial_date", str(ini_time_for_now))
new_final_time = ini_time_for_now + timedelta(days=5)
print("new_final_time", str(new_final_time))
print('Time difference:', str(new_final_time -ini_time_for_now))

