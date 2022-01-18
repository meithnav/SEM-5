female(manisha).
female(sneha).
female(trushi).
female(neeta).
male(meith).
male(jatan).
male(hitesh).
male(bhavesh).
male(dhishil).
male(prakash).
parent(jatan,hitesh).
parent(jatan,bhavesh).
parent(prakash,manisha).
parent(hitesh,meith).
parent(prakash,sneha).
parent(manisha,meith).
parent(sneha,trushi).
parent(sneha,dhishil).
parent(bhavesh,trushi).
parent(bhavesh,dhishil).
mother(X,Y):- parent(X,Y),female(X).
father(X,Y):- parent(X,Y),male(X).
haschild(X):- parent(X,_).
sister(X,Y):- parent(Z,X),parent(Z,Y),female(X),X\==Y.
brother(X,Y):-parent(Z,X),parent(Z,Y),male(X),X\==Y.
grandparent(X,Y):-parent(X,Z),parent(Z,Y).
grandmother(X,Z):-mother(X,Y),parent(Y,Z).
grandfather(X,Z):-father(X,Y),parent(Y,Z).
wife(X,Y):-parent(X,Z),parent(Y,Z),female(X),male(Y).
uncle(X,Z):-brother(X,Y),parent(Y,Z).


