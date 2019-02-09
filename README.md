# Algorithm_genetic

Explanation:

At the end of our work, we used the original structure of each indiviu (phenotype)
without going through another representation (genotype), suddenly any kind of transformation
Gender mutation or crossover was done on the phenotype form of the individual.
At the beginning, and after the generation of the first population of 100 individuals, we have
chose the top 10 individuals who have maximum fitness value in order to generate a
again a population from these and obviously using the operations
mutation and crossing. So the principle is this: at the beginning we generate our
base population of 100 individuals and keep the top 10 individuals with a
high probability of being close to the targeted password, and from these 10 individuals one makes
the two mutating operations and the crossing in parallel to generate the new
population, this procedure is done several times until convergence towards a value of
fitness equal to 1, ie to approach the targeted password.

the mutation operation:

Mainly, after keeping in each iteration the 10 best individuals, and
in use the mutation on each of these individuals one makes the generation of a new
population of self, for that we take each individual and precisely we take a character
(gene) randomly of its 10 characters and is made to change on the one hand by the character
precedent and secondly by the following character, so for each character origin
modified we will have 2 individuals. So, globally using this approach, we necessarily
evolve the top 10 individuals, as we are sure that from an individual "of good
quality "to create at least one individual of better quality and tends towards a solution.
