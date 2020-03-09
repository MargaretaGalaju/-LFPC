#include<stdlib.h>
#include<string.h>
#include<ctype.h>
#include<bits/stdc++.h>

using namespace std;

int main() {
	char character;
	char arr[15];
	char b[30];
	ifstream input_file("my_code.txt");
	int symbol[1000] = { 0 };
	int i, j = 0, kc = 0, ic = 0, lc = 0, mc = 0, nc = 0, oc = 0, _char = 0;
	vector < string > k;
	vector < char > identifiators;
	vector < char > math_operations;
	vector < string > numbers;
	vector < char > separators;


	while (!input_file.eof()) {

		character = input_file.get();
		for (i = 0; i < 12; ++i) {
			if (character == separator[i]) {
				int newC = character;
				if (symbol[newC] != 1) {
					separators.push_back(character);
					symbol[newC] = 1;
					++oc;
				}
			}
		}

		for (i = 0; i < 5; ++i) {
			if (character == mathOperations[i]) {
				int newC = character;
				if (symbol[newC] != 1) {
					math_operations.push_back(character);
					symbol[newC] = 1;
					++mc;
				}
			}
		}
		if (isdigit(character) || character == '.' || character == ' ' || 
			character == '\n' || character == ';') {

			if ((character == ' ' || character == '\n' || character == ';') && (_char != 0)) {
				b[_char] = '\0';
				_char = 0;
				char arr[30];
				strcpy(arr, b);
				numbers.push_back(arr);
				++nc;

			}
		}


		if (isalnum(character)) {
			arr[j++] = character;
		}
		else if ((character == ' ' || character == '\n') && (j != 0)) {
			arr[j] = '\0';
			j = 0;

			if (isKeyword(arr) == 1) {

				k.push_back(arr);
				++kc;
			}
			else {



				if (arr[0] >= 97 && arr[0] <= 122) {
					if (symbol[arr[0] - 'a'] != 1) {
						identifiators.push_back(arr[0]);
						++ic;
						symbol[arr[0] - 'a'] = 1;
					}

				}

			}

		}

	}

	input_file.close();
	printf("The keywords are:  ");
	for (int f = 0;f < kc;++f) {
		if (f == kc - 1) {
			cout << k[f] << "\n";
		}
		else {
			cout << k[f] << ", ";
		}
	}
	printf("The identifiers are: ");
	for (int f = 0;f < ic;++f) {
		if (f == ic - 1) {
			cout << identifiators[f] << "\n";
		}
		else {
			cout << identifiators[f] << ", ";
		}
	}
	printf("The math operators: ");
	for (int f = 0;f < mc;++f) {
		if (f == mc - 1) {
			cout << math_operations[f] << "\n";
		}
		else {
			cout << math_operations[f] << ", ";
		}
	}
	printf("The numerical values: ");
	for (int f = 0;f < nc;++f) {
		if (f == nc - 1) {
			cout << numbers[f] << "\n";
		}
		else {
			cout << numbers[f] << ", ";
		}

	}
	printf("The separators: ");
	for (int f = 0;f < oc;++f) {
		if (f == oc - 1) {
			cout << separators[f] << "\n";
		}
		else {
			cout << separators[f] << " ";
		}
	}

	return 0;
}


int isKeyword(char buffer[]) {
	char keywords[32][10] =
	{ "func","int",,"return","main""char","double","float","long" };
	int i, flag = 0;

	for (i = 0; i < 32; ++i) {
		if (strcmp(keywords[i], buffer) == 0) {
			flag = 1;
			break;
		}
	}

	return flag;
}