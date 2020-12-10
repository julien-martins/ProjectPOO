# -*- coding: utf-8 -*- 

# Pour la file de priorité
from priority_dict import priority_dict


def RoutardApprox ( G ) : # retourne une liste représentant un cycle du graphe G 
	def set_priority_queue ( V ) : # retourne un dictionnaire de priorité dont les clés sont les éléménts de la liste V
		F = priority_dict()
		for v in V :
			F [ v ] = float('inf')
		return F
	def ACM_Prim ( G, root ) : # retourne un arbre couvrant de poid minimum
		F = set_priority_queue ( list ( G.keys ( ) ) )
		parentVertex = {}
		key = {}
		graph = {}
		for u in G :
			key [ u ] = float('inf')
			graph [ u ] = {}
		parentVertex [ root ] = None
		F [ root ] = 0
		key [ root ] = 0
		parentVertex [ root ] = None
		while F :
			u = F.pop_smallest ( )
			for v in  G [ u ] :
				if v in F and G [ u ] [ v ] < key [ v ] :
					parentVertex [ v ] = u
					F [ v ] = G [ u ] [ v ]
					key [ v ] = G [ u ] [ v ]
		for v in G :
			if parentVertex [ v ] != None :
				graph [ v ] [ parentVertex [ v ] ] = G [ v ] [ parentVertex [ v ] ]
				graph [ parentVertex [ v ] ] [ v ] = G [ parentVertex [ v ] ] [ v ]
		return graph
	def do_tree_traversal ( G, vertex ) : # effectue parcours préfixe d'un arbre et retourne une liste des sommets parcourue
		def aux ( G, vertex, sequence ) :
			sequence.append ( vertex )
			for v in G [ vertex ] :
				if v not in sequence :
					sequence = aux ( G, v, sequence )
			return sequence
		return aux ( G, vertex, [] )
	def get_shortest_path ( G, a, b ) :  # retourne une liste représentant le plus court chemin entre deux sommet du graphe
		def get_Dijkstra ( G, source ) :
			parentVertex = {}
			d = {}
			graph = {}
			for v in list ( G.keys ( ) ) :
				d [ v ] = float ( 'inf' )
				parentVertex [ v ] = None
			d [ source ] = 0
			F = set_priority_queue ( list ( G.keys ( ) ) )	
			X = []
			while F :
				u = F.pop_smallest ( )
				if u not in X : X.append ( u )
				for v in G [ u ] :
					if d [ v ] >  d [ u ] + G [ u ] [ v ] :
						d [ v ] = d [ u ] + G [ u ] [ v ]
						F [ v ] = d [ u ] + G [ u ] [ v ]
						parentVertex [ v ] = u
			return parentVertex
		paths = get_Dijkstra ( G, a )
		shortestPath = [ b ]
		while a not in shortestPath :
			for v in paths :
				if v == b :
					b = paths [ v ]
					shortestPath = [ b ] + shortestPath
					break
		return shortestPath
	racine = list ( G.keys ( ) ) [ 0 ]
	T = ACM_Prim ( G, racine )
	rho = do_tree_traversal ( T, racine )
	sigma = [ rho [ 0 ] ]
	for j in range ( 0, len ( rho ) - 1 ) :
		mu = get_shortest_path ( G, rho [ j ], rho [ j + 1 ] )
		sigma = sigma + mu [ 1 : ]
	mu = get_shortest_path ( G, rho [ len ( rho ) - 1 ], rho [ 0 ] )
	sigma = sigma + mu [ 1 : ]
	return sigma

def ConstruireGrapheDifficile ( n ) : #Utiliser python3 et non python
	G = {}
	sequence = [ "v0" ]
	weight = 1
	for i in range ( 0, n - 1 ) :
		currentV = "v" + str ( i )
		nextV = "v" + str ( i + 1 )
		sequence.append ( nextV )
		if currentV not in G : G [ currentV ] = {}
		if nextV not in G : G [ nextV ] = {}
		G [ currentV ] [ nextV ] = weight
		G [ nextV ] [ currentV ] = weight
	sequence.append ( "v0" )
	G [ "v0" ] [ nextV ] = weight
	G [ nextV ] [ "v0" ] = weight
	edgeId = ( n // 2 ) + 1
	vertex1 = "v" + str ( edgeId - 1 )
	vertex2 = "v" + str ( edgeId )
	G [ vertex1 ] [ vertex2 ] = 2
	G [ vertex2 ] [ vertex1 ] = 2
	return ( G, sequence )

if __name__ == "__main__":
	pass
