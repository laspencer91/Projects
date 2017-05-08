/**
Project 2
This program takes users console input and creates instances based on that
information. It then formats and displays Item and summary information of
all items that have been added.

Author: Logan Spencer
Date Modified: February 25, 2017

**/
#include <iostream>
using namespace std;

/***********************  ITEM SUPERCLASS  ********************************/
/** Item class that contains all the shared variables of its child classes.
Pens, Printer, SafeBox **/
class Item
{
	protected:
		int code, color, brand, type, itemNum; // Attributes 1-3, 9, and 11 (Packet Size, tray capacity, electronic lock
		double height, length, width, weight, price; // Attributes 4 - 8;
		bool att10; // Attribute 10 (Assorted ink, network, fire resistant)

	public:
		// Default empty Constructor
		Item();
		Item(const Item& item); // Copy Constructor
		virtual void copy(const Item& item); // Calls Copy Constructor

		// Parameterized Constructor
		Item(int myItemNum, int myCode, int myColor, int myBrand, double myHeight, double myLength, double myWidth,
			double myWeight, double myPrice, int myType, bool a10);

		// Get and set methods used to set and recieve information from an outside class
		int getCode();
		double getPrice();
		bool getAtt10();
		int getItemNum();

		void setColor(int Color);
		void setHeight(double Height);
		void setLength(double Length);
		virtual ~Item() {};
		virtual void operator=(const Item& it2);
		friend ostream& operator<<(ostream& out, Item& obj);
		virtual void display();
};

// Default empty Constructor
Item::Item() {};

// Copy Constructor
Item::Item(const Item& it2) 
{
	if (&it2 != this)
		copy(it2);
}

void Item::copy(const Item& it2) {
	code = it2.code;       brand = it2.brand;
	color = it2.code;      type = it2.brand;
	itemNum = it2.itemNum; height = it2.height;
	length = it2.length;   width = it2.width;
	weight = it2.weight;   price = it2.price;
	att10 = it2.att10;
}

// Items Parameterized Constructor
Item::Item(int myItemNum, int myCode, int myColor, int myBrand, double myHeight, double myLength, double myWidth,
	double myWeight, double myPrice, int myType, bool a10)
{
	itemNum = myItemNum;
	code = myCode;
	color = myColor;
	brand = myBrand;
	height = myHeight;
	length = myLength;
	width = myWidth;
	weight = myWeight;
	price = myPrice;
	type = myType;
	att10 = a10;
}

// Get and set methods used to recieve and update information from an outside class
int Item::getCode() { return code; }

double Item::getPrice() { return price; }

bool Item::getAtt10() { return att10; }

int Item::getItemNum() { return itemNum; }

void Item::setColor(int Color) { color = Color; }

void Item::setLength(double Length) { length = Length; }

void Item::setHeight(double Height) { height = Height; }

void Item::operator=(const Item& it2) {
	if (&it2 != this)
		copy(it2);
}

ostream& operator<<(ostream& out, Item& obj) {
	obj.display();
	return out;
}
void Item::display() {}

// End Get / Set methods ---------------------------------
// ----------------------------------End Item Class------------------------//



/**************************  PEN CLASS   ********************************/
/** Pens class inherits from Item. This class describes a Pen instance **/
class Pens : public Item
{
private:
	int packetSize; // Size of the pen packet

public:
	Pens(); // Empty default constuctor
	Pens(const Pens& it2); // Default copy constructor
	void copy(const Pens& it2); // Calls copy constructor

			// Constuctor with variable initializations
	Pens(int myItemNum, int myCode, int myColor, int myBrand, double myHeight, double myLength, double myWidth,
		double myWeight, double myPrice, int myType, bool a10, int myPacketSize);

	// Specific field for the Item "pen"
	int getPacketSize();

	// Display the correct description of this Pen item
	~Pens(){};
	void operator=(const Pens& it2);
	void display();
};

// Default empty Constructor
Pens::Pens() {};

// Copy Constructor
Pens::Pens(const Pens& it2)
{
	if (&it2 != this)
		copy(it2);
}

void Pens::copy(const Pens& it2) 
{
	// Inherited Variables
	code = it2.code;       brand = it2.brand;
	color = it2.code;      type = it2.brand;
	itemNum = it2.itemNum; height = it2.height;
	length = it2.length;   width = it2.width;
	weight = it2.weight;   price = it2.price;
	att10 = it2.att10;
	// Class specific variable
	packetSize = it2.packetSize;
}

// Parameterized constructor for Pens
Pens::Pens(int myItemNum, int myCode, int myColor, int myBrand, double myHeight, double myLength, double myWidth,
	double myWeight, double myPrice, int myType, bool a10, int myPacketSize)
{
	itemNum = myItemNum;
	code = myCode;
	color = myColor;
	brand = myBrand;
	height = myHeight;
	length = myLength;
	width = myWidth;
	weight = myWeight;
	price = myPrice;
	type = myType;
	att10 = a10;
	packetSize = myPacketSize;
}

// Display the correct description of this Pen item
void Pens::display()
{
	cout << "Pen, ";
	// Select proper label for color
	switch (color)
	{
		case 1: cout << "Red, ";
			break;
		case 2: cout << "Blue, ";
			break;
		case 3: cout << "Green, ";
			break;
		case 4: cout << "Black, ";
			break;
	}

	// Select proper label for brand
	switch (brand)
	{
		case 1: cout << "Pilot, ";
			break;
		case 2: cout << "Paper Mate, ";
			break;
		case 3: cout << "Uni-Ball, ";
			break;
		case 4: cout << "Sharpie, ";
			break;
	}

	// Display height, length, width, weight, and price
	cout << "H: " << height << ", L: " << length << ", W: " << width << ", Weight: " << weight << ", $" << price << ", ";

	// Select proper label for brand
	switch (type)
	{
		case 1: cout << "Ball Point, ";
			break;
		case 2: cout << "Roller Ball, ";
			break;
		case 3: cout << "Fountain, ";
			break;
		case 4: cout << "Calligraphy, ";
			break;
	}

	// Add word "not" before "assorted ink" if its not true
	if (att10 == false)
		cout << "Not ";

	cout << "Assorted Ink, " << packetSize << endl;
}

void Pens::operator=(const Pens& it2) {
	if (&it2 != this)
		copy(it2);
}

// Get the value of packetSize
int Pens::getPacketSize() {
	return packetSize;
}
//------------------------------------- End of Pens Class ------------------------------------------//




/**************************  PRINTER SUBCLASS   ********************************/
/** Printer class inherits from Item. This class describes a Printer instance **/
class Printer : public Item
{
	private:
		int trayCapacity; // Item specific variable that holds how much paper this can hold

	public:
		Printer(); // Empty default constuctor
		Printer(const Printer& it2); // Copy Constructor
		void copy(const Printer& it2); // Calls Copy Constructor

				   // Parameterized constructor
		Printer(int myItemNum, int myCode, int myColor, int myBrand, double myHeight, double myLength, double myWidth,
			double myWeight, double myPrice, int myType, bool a10, int myTrayCapacity);

		// Get the value of trayCapacity
		int getTrayCapacity();

		// Displays the correct text information into the console about this instance of a printer
		virtual ~Printer() {}; 
		void operator=(const Printer& it2);
		void display();
};

// Default empty Constructor
Printer::Printer() {};

// Copy Constructor
Printer::Printer(const Printer& it2)
{
	if (&it2 != this)
		copy(it2);
}

void Printer::copy(const Printer& it2) 
{
	// Inherited Variables
	code = it2.code;       brand = it2.brand;
	color = it2.code;      type = it2.brand;
	itemNum = it2.itemNum; height = it2.height;
	length = it2.length;   width = it2.width;
	weight = it2.weight;   price = it2.price;
	att10 = it2.att10;
	// Class specific variable
	trayCapacity = it2.trayCapacity;
}

// Parameterized constructor for Printer
Printer::Printer(int myItemNum, int myCode, int myColor, int myBrand, double myHeight, double myLength, double myWidth,
	double myWeight, double myPrice, int myType, bool a10, int myTrayCapacity)
{
	itemNum = myItemNum;
	code = myCode;
	color = myColor;
	brand = myBrand;
	height = myHeight;
	length = myLength;
	width = myWidth;
	weight = myWeight;
	price = myPrice;
	type = myType;
	att10 = a10;
	trayCapacity = myTrayCapacity;
}

// Display correct instance information into the console
void Printer::display()
{
	cout << "Printer, ";

	// Select proper label for color
	switch (color)
	{
		case 1: cout << "Black, "; break;
		case 2: cout << "White, "; break;
		default: cout << "Undefined Color, "; break;
	}

	// Select proper label for brand
	switch (brand)
	{
		case 1: cout << "Dell, "; break;
		case 2: cout << "HP, "; break;
		case 3: cout << "Cannon, "; break;
		case 4: cout << "Brother, "; break;
		default: cout << "Undefined Brand, ";
	}

	// Display height, length, width, weight, and price
	cout << "H: " << height << ", L: " << length << ", W: " << width << ", Weight: " << weight << ", $" << price << ", ";

	// Select proper label for brand
	switch (type)
	{
		case 1: cout << "All-In-One, "; break;
		case 2: cout << "Laser, "; break;
		case 3: cout << "Ink-Jet, "; break;
		default: cout << "Undefined Type, ";
	}

	// If Network is set to false include the word "Not" before the next line
	if (att10 == false)
		cout << "Not ";

	// Finish display line
	cout << "A Network Connected Printer, Tray Capacity: " << trayCapacity << endl;
}

void Printer::operator=(const Printer& it2) {
	if (&it2 != this)
		copy(it2);
}

// Get the value of trayCapacity
int Printer::getTrayCapacity() {
	return trayCapacity;
}
//------------------------------- End Printer Class ------------------------------------------------//



/**************************  SAFEBOX SUBCLASS   ********************************/
/** SafeBox class inherits from Item. This class describes a SafeBox instance **/
class SafeBox : public Item
{
private:
	// Specific variable for the SafeBox item
	int electronicLock;

public:
	// Default empty constuctor
	SafeBox();
	SafeBox(const SafeBox& it2); // Default Copy Constructor
	void copy(const SafeBox& it2); // Calls Copy Constructor (This does the copying)

	// Parameterized Constructor
	SafeBox(int myItemNum, int myCode, int myColor, int myBrand, double myHeight, double myLength, double myWidth,
		double myWeight, double myPrice, int myType, bool a10, int myElectronicLock);

	virtual ~SafeBox() {};
	void operator=(const SafeBox& it2);
	void display();

	int getElectronicLock();
};

// Default empty Constructor
SafeBox::SafeBox() {};

// Copy Constructor
SafeBox::SafeBox(const SafeBox& it2)
{
	if (&it2 != this)
		copy(it2);
}

void SafeBox::copy(const SafeBox& it2) 
{
	// Inherited Variables
	code = it2.code;       brand = it2.brand;
	color = it2.code;      type = it2.brand;
	itemNum = it2.itemNum; height = it2.height;
	length = it2.length;   width = it2.width;
	weight = it2.weight;   price = it2.price;
	att10 = it2.att10;
	// Class specific variable
	electronicLock = it2.electronicLock;
}

// Complete constructor with variable initialization
SafeBox::SafeBox(int myItemNum, int myCode, int myColor, int myBrand, double myHeight, double myLength, double myWidth,
	double myWeight, double myPrice, int myType, bool a10, int myElectronicLock)
{
	itemNum = myItemNum;
	code = myCode;
	color = myColor;
	brand = myBrand;
	height = myHeight;
	length = myLength;
	width = myWidth;
	weight = myWeight;
	price = myPrice;
	type = myType;
	att10 = a10;
	electronicLock = myElectronicLock;
}

// Return the value of electronicLock
int SafeBox::getElectronicLock()
{
	return electronicLock;
}

// Displays the correct text information int the console 
// about this instance of a printer
void SafeBox::display()
{
	cout << "Safe Box, ";

	// Select proper label for color
	switch (color)
	{
		case 1: cout << "Black, ";
			break;
		case 2: cout << "Red, ";
			break;
		default: cout << "Undefined Color, ";
			break;
	}

	// Select proper label for brand
	cout << "Brand: ";
	switch (brand)
	{
		case 1: cout << "SentrySafe, ";
			break;
		case 2: cout << "First Alert, ";
			break;
		case 3: cout << "Honeywell, ";
			break;
		default: cout << "Undefined Brand, ";
	}

	// Display height, length, width, weight, and price
	cout << "H: " << height << ", L: " << length << ", W: " << width << ", Weight: " << weight << ", $" << price << ", ";

	// Select proper label for brand
	switch (type)
	{
		case 1: cout << "Water Proof, ";
			break;
		case 2: cout << "Wall Mountable, ";
			break;
		case 3: cout << "Desktop, ";
			break;
		default: cout << "Undefined Type, ";
			break;
	}

	// If Network is set to false include the word "Not" before the next line
	if (att10 == false)
		cout << "Not ";

	cout << "Fire Resistant, ";

	// If Electronic lock is set to false include the words "Doesnt have"
	if (electronicLock == false)
		cout << "Doesn't have ";

	cout << "Electronic Lock" << endl;
}

void SafeBox::operator=(const SafeBox& it2) {
	if (&it2 != this)
		copy(it2);
}

// ---------------------- End Safe Box -------------------------------------------------//


/*****************************  BAG CLASS   ***********************************/
/** Bag instance thatt describes items in a bag and performs array functions **/
class Bag
{
	private:
		// Need to use double pointer here to be able to access the subclass methods
		Item** myItems;
		int numItems = 0;

	public:
		Bag();                            // Default constructor
		Bag(const Bag& it2);			  // Copy Constructor
		void copy(const Bag& it2);        // Copy Constructor
		void addItem(Item* itemToAdd);    // Add item to the bag
		void delItem(int itemNum);        // Delete an item from the bag
		void resizeArray(int sizeChange); // Resize Array up or down
		~Bag();                           // Delete the values in myItems as well as the array itself
		void getStats(int myID);          // Calculate the stats for my bag, and display results.
		void operator=(const Bag& it2);
		friend ostream& operator<<(ostream& stream, const Bag& obj);
};

// Default constructor, creates space in my bag for 1 item.
Bag::Bag()
{
	myItems = new Item*[1];
}

// Default Copy Constructor
Bag::Bag(const Bag& it2)
{
	if (&it2 != this)
		copy(it2);
}

// Called From Default Copy constuctor
void Bag::copy(const Bag& it2) {
	// Copy My Items Array
	numItems = it2.numItems;
	myItems = new Item*[numItems];

	for (int i = 0; i < numItems; i++) {
		(*myItems[i]) = (*it2.myItems[i]);
	}
}

// Calculate the stats for my bag, and display results.
void Bag::getStats(int myID)
{
	int numPens = 0,
		numPrinters = 0,
		numSafeBoxes = 0;

	double penPrice = 0,
		printerPrice = 0,
		safeBoxPrice = 0;

	// Get the code of the specific item of the Bag array of index i.
	for (int i = 0; i < numItems; i++)
	{
		// This code holds what type of item it is. ( 1 - Pen, 2 - Printer, 3 - SafeBox )
		switch (myItems[i]->getCode())
		{
		case 1:
			numPens++;
			penPrice += myItems[i]->getPrice(); // Add the price of specific pen to the total
			break;
		case 2:
			numPrinters++;
			printerPrice += myItems[i]->getPrice(); // Add price of specific printer to the total
			break;
		case 3:
			numSafeBoxes++;
			safeBoxPrice += myItems[i]->getPrice(); // Add price of specific safeBox to the total
			break;
		default:
			cout << "Undefined Object";
			break;
		}
	}

	// Display summary statistics
	cout << endl << "Bag Summary for USER ID: " << myID << endl << "---------------------------------------------" << endl;
	cout << "Total Pens: " << numPens << "        Total Pen Value: $" << penPrice << endl; // Display Pen information
	cout << "Total Printers: " << numPrinters << "   Total Printer Value: $" << printerPrice << endl; // Display Printer Information
	cout << "Total Safe Boxes: " << numSafeBoxes << "  Total Safe Box Value: $" << safeBoxPrice << endl << endl; // Print Safe Box Information
	cout << "Total Item Values: $" << (penPrice + printerPrice + safeBoxPrice) << endl << endl; // Printer total price
}

/** Add item: if this is not the first occurance then we need to
resize the array by adding a space. Next we insert the new objects
and increase the count for how many objects we arte holding. **/
void Bag::addItem(Item* itemToAdd)
{
	// Resize array only if this isn't the first item in the bag
	// since the array is initialized with a size of 1
	if (numItems > 0)
	{
		resizeArray(1);
	}
	// Insert New Object
	myItems[numItems] = itemToAdd;
	numItems++;
}

/** Delete item: if this is not the first occurance then we need to
resize the array by adding a space. Next we insert the new objects
and increase the count for how many objects we arte holding. **/
void Bag::delItem(int itemNum)
{
	/**Search through myItems to see if any of the item numbers
	match the value of itemNum, if so delete that item  **/
	for (int i = 0; i < numItems; i++)
	{
		if (myItems[i]->getItemNum() == itemNum)
		{
			// Actually erases this item from memory
			delete myItems[i];

			// Makes the new value of this memory location predictable
			myItems[i] = NULL;

			// Restructure remaining array
			if (i < numItems)
			{
				for (int a = i; a < numItems - 1; a++)
				{
					// Shift over the remaining elements
					myItems[a] = myItems[a + 1];
					myItems[a + 1] = NULL;
				}
				/** We want to recheck the current index since it has
				now been changed. myItems[i] was removed then replaced
				with myItems[i+1] so we need to check its value again. */
				if (i > -1) { i--; }
			}
			numItems--;
			// Last step resize array using size of numItems because
			// numItems was decreased before this so we pass 0 as the parameter
			resizeArray(0);
		}
	}
}

/** Resize array function. Using double pointers prevents slicing.
First we create a temp array that copies the contents of myItems.
Then we "reset" my items and transfer the data back. Finally we
delete the temp array. **/
void Bag::resizeArray(int sizeChange)
{
	Item** temp = myItems; // Set temp equal to the array of myItems

	myItems = new Item*[numItems + sizeChange];
	for (int i = 0; i < numItems; i++)
	{
		myItems[i] = temp[i];
		temp[i] = NULL;
		delete temp[i];
	}
}

// This display function simply calls the display function of each item in
// our bag.
ostream& operator<<(ostream& out, const Bag& obj)
{
	// Cycle through each value in bag and display its properties
	for (int i = 0; i < obj.numItems; i++)
	{
		out << *obj.myItems[i];
	}
	return out;
}

// Delete the values in myItems as well as the array itself
Bag::~Bag()
{
	for (int i = 0; i < numItems; i++)
	{
		delete myItems[i];
	}
	delete[] myItems;
}

void Bag::operator=(const Bag& it2) {
	if (&it2 != this)
		copy(it2);
}

/**************************  CLASSLESS METHODS   ********************************/

// Set all char values in an array to an empty char value
void terminateString(char* str, int size)
{
	for (int i = 0; i < size; i++)
	{
		str[i] = '\0';
	}
}

/** Search for a user of userID in the array of storage with storage having
a size of arrSize. If userID value is found in the array we return the
location, or else we return -1 telling us that it was not found.**/
int searchForUser(int userID, int storage[], int arrSize)
{
	for (int i = 0; i < arrSize; i++)
	{
		// If the current index of storage matches the userID passed in
		if (storage[i] == userID)
		{
			// Return this location
			return i;
		}
	}
	return -1; // If userID was not found
}

// Compare the exactness of two arrays of characters of a specific array size
bool compareString(char str1[], char str2[], int size)
{
	for (int i = 0; i < size; i++)
	{
		if (str1[i] != str2[i])
			return false;
	}
	return true;
}


/** The main method that runs the program. This method takes the users input,
stores item instances in an array, and formats the displayed results **/
int main()
{
	int users[50]; // Create 50 slots available for user ID's
	Bag* userBag[50]; // Bag of Items. Pointers to Bag object
	Pens* onePen;
	Printer* onePrinter;
	SafeBox* oneSafeBox;
	int numUsers = 0;
	int placementCursor;

	char addItemString[] = { 'A', 'd', 'd', 'I', 't', 'e', 'm', '\0', '\0', '\0' }; // String to use for command comparisons
	char delItemString[] = { 'D', 'e', 'l', 'I', 't', 'e', 'm', '\0', '\0', '\0' }; // String to use for command comparisons
	char command[10]; // Takes input of user and compares this to addItemString

					  // Set all values of command to an empty value
	terminateString(command, 10);

	// Input variables used to create an instance of Item with the proper
	// values
	int quantity, code, color, brand, type, att11, userID, itemNum;
	double height, length, width, weight, price;
	bool att10;

	// Label the Input Section
	cout << "Input" << endl << "---------------------------------------------" << endl;

	// While we are not at the end of the line keep asking for user input
	while (cin.good())
	{
		// Get command input for user
		cin >> command >> userID >> itemNum;;

		if (compareString(command, addItemString, 10))
		{
			// Rest of Input of add command
			cin >> quantity >> code >> color >> brand >> height >> length >> width
				>> weight >> price >> type >> att10 >> att11;

			// Print out what was just input
			cout << command << " " << userID << " " << itemNum << " " << quantity << " " << code << " " << color << " " << brand << " "
				<< height << " " << length << " " << width << " " << weight << " " << price << " "
				<< type << " " << att10 << " " << att11 << endl;
			placementCursor = searchForUser(userID, users, numUsers); // Find where to place new items

			// If user was found just add the item to that users Bag
			if (placementCursor != -1)
			{
				for (int i = 0; i < quantity; i++)
				{
					switch (code)
					{
						case 1:  // Add Pens
							onePen = new Pens(itemNum, code, color, brand, height, length, width, weight, price, type, att10, att11);
							userBag[placementCursor]->addItem(onePen);
							break;
						case 2:  // Add Printer
							onePrinter = new Printer(itemNum, code, color, brand, height, length, width, weight, price, type, att10, att11);
							userBag[placementCursor]->addItem(onePrinter);
							break;
						case 3:  // Add SafeBox
							oneSafeBox = new SafeBox(itemNum, code, color, brand, height, length, width, weight, price, type, att10, att11);
							userBag[placementCursor]->addItem(oneSafeBox);
							break;
					}
				}
			}
			else // If user ID was not found we need to create it
			{
				users[numUsers] = userID; // Assign this userBag to hold the new userID
				userBag[numUsers] = new Bag();
				for (int i = 0; i < quantity; i++)
				{
					switch (code)
					{
						case 1:  // Add Pens
							onePen = new Pens(itemNum, code, color, brand, height, length, width, weight, price, type, att10, att11);
							userBag[numUsers]->addItem(onePen);
							break;
						case 2:  // Add Printer
							onePrinter = new Printer(itemNum, code, color, brand, height, length, width, weight, price, type, att10, att11);
							userBag[numUsers]->addItem(onePrinter);
							break;
						case 3:  // Add SafeBox
							oneSafeBox = new SafeBox(itemNum, code, color, brand, height, length, width, weight, price, type, att10, att11);
							userBag[numUsers]->addItem(oneSafeBox);
							break;
					}
				}
				numUsers++; // Increase the total number of users
			}
		}
		// If command is to delete an item
		else if (compareString(command, delItemString, 10))
		{
			// Print out what was just input
			cout << command << " " << userID << " " << itemNum << endl;

			// Check where the user exists
			placementCursor = searchForUser(userID, users, numUsers);

			// If user was found just add the item to that users Bag
			if (placementCursor != -1)
			{
				userBag[placementCursor]->delItem(itemNum);
			}
		}
	}

	// Label the Item Properties Section
	cout << endl << "Item Properties" << endl << "---------------------------------------------" << endl;

	// Cycle through every user and tell them to display
	// all their stored items.
	for (int i = 0; i < numUsers; i++)
	{
		cout << *userBag[i];
	}

	// Cycle through each value in every users bag and display its properties
	for (int i = 0; i < numUsers; i++)
	{
		// users[] is passed in just to display the userID in the header of the display
		userBag[i]->getStats(users[i]);
	}

	// Collect Garbage ( Destroy dynamically created objects )
	for (int i = 0; i < numUsers; i++)
	{
		delete userBag[i];
	}
	getchar();
	return 0;
}