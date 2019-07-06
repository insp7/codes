# --------------------- Exchange Digits ---------------------
# Compute nearest larger number by interchanging digits updated.
# Given 2 numbers a and b find the smallest number greater than b by interchanging the digits of a and if not possible print -1
# Constraints: 1 <= a, b <= 10000000

# Example 1 
# a = 459, b = 500; Output = 549

# Example 2
# a = 645757, b = 457765; Output = 465577

# Example 3
# a = 5964, b = 9984; Output = -1

# SOLUTION: 

# Returns a list of digits of a given number
def convertDigitsOfIntegerToList(number):
	number = str(number) # Convert integer to string
	list(number) # Make list of individual string characters
	number = list(map(int, number)) # Convert the list of characters into list of integers

	return number

# Returns the maximum possible number formed indicated by the number of digits. 
# Ex. if digits = 4, maxNum = 9999
def getMaxNumber(digits):
	if digits == 0:
		return 0

	maxNumberStr = ''

	for x in range(1, (digits + 1)):
		maxNumberStr += '9'

	return int(maxNumberStr)

# Returns a Hashmap(Python dictionary) for the given number of digits.
# Ex. if number = 12, hashMap = {1: 1, 2: 2}
def getHashMapOfDigits(number):
	number = convertDigitsOfIntegerToList(number)

	hashMapA = {}
	for i in number:
		if i in hashMapA:
			hashMapA[i]
		hashMapA[i] = i

	return hashMapA

# Returns a Hashmap consisting of frequency of individual digits in a number
def getFrequencyOfDigits(number): 
	number = convertDigitsOfIntegerToList(number)

	frequency = {}
	for key in number:
		if key in frequency:
			frequency[key] = frequency[key] + 1
		else:
			frequency[key] = 1

	return frequency 

# Returns the number of digits in the given number
def getDigitsCount(number):
	return len(str(number))

# Initialize input for testing
a = 645757
b = 457765

digitsCount = getDigitsCount(a)
maxNumber = getMaxNumber(digitsCount)
hashMapA = getHashMapOfDigits(a)
frequency = getFrequencyOfDigits(a)
flag = False

# CORE LOGIC
for i in range(b + 1, maxNumber + 1):
	digits = convertDigitsOfIntegerToList(i)
	hashMapACopy = hashMapA.copy() # Make a copy of the dictionary 
	frequencyCopy = frequency.copy() # Make a copy of the frequency
		
	for digit in digits:
		if digit in hashMapACopy: # if this digit exists in the hashmap
			flag = True

			if frequencyCopy[digit] == 1: 
				del hashMapACopy[digit] # frequency of digit is only one, remove the key from the hashmap
			elif frequencyCopy[digit] > 1: 
				frequencyCopy[digit] = frequencyCopy[digit] - 1 # frequency of digit is more than once, decrement the frequencyCount for that key
		else:
			flag = False
			break

	if bool(flag):
		print(i)
		break

if bool(flag) == False:
	print("-1")
