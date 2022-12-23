# Bottom-up-LR-Parser

Grammar:
![image](https://user-images.githubusercontent.com/55497608/209395935-0f14959c-d6dc-415e-9d8f-12678ae0d5e5.png)
LR Parsing Table:
Your program will take one string ending with $ as an input and will create the output file with
the contents of the Stack, Input, and Action as explained in the lecture.
Sample Run:
Your code will take the input from the console and will output the contents of Stack, Input, and
Action to an output file as in the following example where the source code, input string and
output file are named hw2_firstname_lastname.java, id+id*id$, and
output.txt, respectively:
java hw2_firstname_lastname.java id+id*id$ output.txt
The input has been parsed successfully.
The output file “output.txt” consists of the following content:
Stack Input Action
0 id + id * id $ Shift 5
0id5 + id * id $ Reduce 6
0F3 + id * id $ Reduce 4
0T2 + id * id $ Reduce 2
0E1 + id * id $ Shift 6
0E1+6 id * id $ Shift 5
0E1+6id5 * id $ Reduce 6
0E1+6F3 * id $ Reduce 4
0E1+6T9 * id $ Shift 7
0E1+6T9*7 id $ Shift 5
0E1+6T9*7id5 $ Reduce 6
0E1+6T9*7F10 $ Reduce 3
0E1+6T9 $ Reduce 1
0E1 $ Accept
Another Run:
java hw2_firstname_lastname.java id++*id$ output.txt
Error occurred.
The content of output.txt:
Stack Input Action
0 id + + * id $ Shift 5
0id5 + + * id $ Reduce 6
0F3 + + * id $ Reduce 4
0T2 + + * id $ Reduce 2
0E1 + + * id $ Shift 6
0E1+6 + * id $ ERROR
