# Bottom-up-LR-Parser

Grammar:<br />
![image](https://user-images.githubusercontent.com/55497608/209395935-0f14959c-d6dc-415e-9d8f-12678ae0d5e5.png)<br />
LR Parsing Table:<br />
![image](https://user-images.githubusercontent.com/55497608/209396528-7d90078d-7ee9-42a2-a633-e3ea6f54f753.png)<br />

Your program will take one string ending with $ as an input and will create the output file with
the contents of the Stack, Input, and Action as explained in the lecture.<br />

Sample Run:<br />

Your code will take the input from the console and will output the contents of Stack, Input, and
Action to an output file as in the following example where the source code, input string and
output file are named hw2_firstname_lastname.java, id+id*id$, and
output.txt, respectively:<br />

java hw2_firstname_lastname.java id+id*id$ output.txt<br />

The input has been parsed successfully.<br />

The output file “output.txt” consists of the following content:<br />

Stack Input Action<br />
0 id + id * id $ Shift 5<br />
0id5 + id * id $ Reduce 6<br />
0F3 + id * id $ Reduce 4<br />
0T2 + id * id $ Reduce 2<br />
0E1 + id * id $ Shift 6<br />
0E1+6 id * id $ Shift 5<br />
0E1+6id5 * id $ Reduce 6<br />
0E1+6F3 * id $ Reduce 4<br />
0E1+6T9 * id $ Shift 7<br />
0E1+6T9*7 id $ Shift 5<br />
0E1+6T9*7id5 $ Reduce 6<br />
0E1+6T9*7F10 $ Reduce 3<br />
0E1+6T9 $ Reduce 1<br />
0E1 $ Accept<br />

Another Run:<br />

java hw2_firstname_lastname.java id++*id$ output.txt<br />

Error occurred.<br />

The content of output.txt:<br />

Stack Input Action<br />
0 id + + * id $ Shift 5<br />
0id5 + + * id $ Reduce 6<br />
0F3 + + * id $ Reduce 4<br />
0T2 + + * id $ Reduce 2<br />
0E1 + + * id $ Shift 6<br />
0E1+6 + * id $ ERROR<br />
