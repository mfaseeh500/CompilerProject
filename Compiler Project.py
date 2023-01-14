INT , ADD , SUB , MUL , DIV, EOF , LP , RP = 'INT' , 'ADD' , 'SUB' , 'MUL' , 'DIV' , 'EOf' , 'LP' , 'RP'


class Token(object):
    def __init__(self,type,value):
        self.type = type
        self.value= value
class Lexer(object):
    def __init__(self,text):
        self.text = text
        self.pos = 0
        self.cc = self.text[self.pos]
    def error(self):
        raise Exception("Invalid token")            
    def advance(self):
        self.pos += 1
        if self.pos > len(self.text) -1 :
            self.cc = None
        else :
            self.cc = self.text[self.pos]
    def skip_white_spaces(self):
        while self.cc is not None and self.cc.isspace():
            self.advance()
    def integer(self):
        result = ''
        while self.cc is not None and self.cc.isdigit(): 
            result += self.cc
            self.advance()                      
        return int(result)
    def get_next_token(self):
        while self.cc is not None :
            if self.cc.isspace():
                self.skip_white_spaces()
                continue
            if self.cc.isdigit():
                return Token(INT, self.integer())
            if self.cc == '+':
                token = Token(ADD,'+')
                self.advance()
                return token
            if self.cc == '-':
                token = Token(SUB, '-')
                self.advance()
                return token
            if self.cc == '*':
                token = Token(MUL, '*')
                self.advance()
                return token
            if self.cc == '/':
                token  =  Token(DIV, '/')
                self.advance()
                return token
            if self.cc == '(':
                token = Token(LP, '(')    
                self.advance()
                return token
            if self.cc == ')':
                token  = Token(RP, ')')    
                self.advance()
                return token
        return Token(EOF, None)            
            

class AST(object):
    pass

class BinOP(AST):
    def __init__(self, left , op: Token , right):
        self.left =left
        self.token = self.op = op
        self.right = right

class Num(AST):

    def __init__(self,token):
        self.token =token
        self.value = token.value        

class Parser(object):

    def __init__(self,lexer : Lexer):
        self.lexer = lexer
        self.ct = self.lexer.get_next_token()
    def error(self):
        raise Exception("Parse Error")
    def verify(self,t_type):
        if self.ct.type == t_type:
            self.ct = self.lexer.get_next_token()
        else :
            self.error()
    def factor(self):
        token = self.ct
        if token.type == INT :
            self.verify(INT)
            return Num(token)
        elif token.type == LP:
            self.verify(LP)
            node = self.expr()
            self.verify(RP)
            return node 
    def term(self):
        node =self.factor()
        while self.ct.type in (MUL,DIV):
            token = self.ct
            if token.type == MUL:
                self.verify(MUL)
            elif token.type == DIV:
                self.verify(DIV)

            node = BinOP(node,token,self.factor())

        return node
    def expr(self):
        node =self.term()
        while self.ct.type in (ADD,SUB):
            token = self.ct
            if token.type == ADD:
                self.verify(ADD)
            elif token.type == SUB:
                self.verify(SUB)

            node = BinOP(node,token,self.term())

        return node
    def parse(self):
        return self.expr()    


class NodeVisitor(object):
    def visit(self, node):
        method = 'visit_'+type(node).__name__
        visitor = getattr(self,method,self.generic_visit)
        return visitor(node)

    def generic_visit(self):
        raise Exception("Method Not found")



class Interpretor(NodeVisitor):
    def __init__(self,parser):
        self.parser = parser    
    def visit_BinOP(self,node):
        if node.op.type == ADD :
            return self.visit(node.left) +self.visit(node.right)
        elif node.op.type == SUB :
            return self.visit(node.left)  - self.visit(node.right)   
        elif node.op.type == MUL :
            return self.visit(node.left)  * self.visit(node.right)   
        elif node.op.type == DIV :
            return self.visit(node.left)  / self.visit(node.right)   
    def visit_Num(self, node):
       
        return node.value
    def interpret(self):
        tree = self.parser.parse()
        return self.visit(tree)            










def main():
    while True:
        try:
            text = input('Enter here : ')
        except:
            print('Error')
        if not text:
            continue
        lex = Lexer(text)
        parser = Parser(lex)
        intepret = Interpretor(parser)
        result = intepret.interpret()
        print(result)


main()        