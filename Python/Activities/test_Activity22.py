import pytest

# Additon
def test_addition():
	
	num1 = 10
	num2 = 22
    
	sum = num1 + num2
	assert sum == 32

# Subtraction
def test_subtraction():
  
	num1 = 50
	num2 = 55
    
	diff = num1 - num2
	assert diff == -5

# Multiplication
@pytest.mark.activity
def test_multiplication():
  
	num1 = 5
	num2 = 50

	prod = num1 * num2
	assert prod == 250

# Division
@pytest.mark.activity
def test_division():
  
	num1 = 500
	num2 = 5
    
	quot = num1 / num2
	assert quot == 100