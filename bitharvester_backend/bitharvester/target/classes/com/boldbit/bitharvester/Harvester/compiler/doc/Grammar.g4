CompilationUnit       ::= PackageDeclaration? ImportDeclaration* TypeDeclaration*

PackageDeclaration   ::= 'package' QualifiedName ';'

ImportDeclaration    ::= 'import' QualifiedName ';'

TypeDeclaration      ::= ClassDeclaration | InterfaceDeclaration

ClassDeclaration     ::= Modifiers? 'class' Identifier ( extends Type )? ( implements TypeList )? ClassBody

InterfaceDeclaration ::= Modifiers? 'interface' Identifier ( extends TypeList )? InterfaceBody

ClassBody            ::= '{' ( ClassBodyDeclaration )* '}'

InterfaceBody        ::= '{' ( InterfaceBodyDeclaration )* '}'

ClassBodyDeclaration ::= FieldDeclaration | MethodDeclaration | ConstructorDeclaration | ClassDeclaration | InterfaceDeclaration | StaticInitializer | InstanceInitializer

InterfaceBodyDeclaration ::= FieldDeclaration | MethodDeclaration | ClassDeclaration | InterfaceDeclaration

FieldDeclaration     ::= Modifiers? Type VariableDeclarators ';'

MethodDeclaration    ::= MethodHeader MethodBody

ConstructorDeclaration ::= Modifiers? ConstructorDeclarator ConstructorBody

MethodHeader         ::= Modifiers? TypeParameters? Result MethodDeclarator Throws?

MethodDeclarator     ::= Identifier '(' ( FormalParameterList? ) ')'

ConstructorDeclarator ::= Identifier '(' ( FormalParameterList? ) ')'

FormalParameterList  ::= FormalParameter ( ',' FormalParameter )*

FormalParameter      ::= Modifiers? Type VariableDeclaratorId

VariableDeclaratorId ::= Identifier

Type                 ::= BasicType | ReferenceType

BasicType            ::= 'byte' | 'short' | 'int' | 'long' | 'float' | 'double' | 'char' | 'boolean'

ReferenceType        ::= ClassType | ArrayType

ClassType            ::= Identifier TypeArguments?

ArrayType            ::= Type '[' ']'

TypeList             ::= Type ( ',' Type )*

TypeArguments        ::= '<' TypeList '>'

Result               ::= Type | 'void'

Throws               ::= 'throws' ExceptionList

ExceptionList        ::= QualifiedName ( ',' QualifiedName )*

QualifiedName       ::= Identifier ( '.' Identifier )*

Modifiers            ::= Modifier*

Modifier             ::= 'public' | 'protected' | 'private' | 'static' | 'final' | 'abstract' | 'synchronized' | 'native' | 'strictfp'
