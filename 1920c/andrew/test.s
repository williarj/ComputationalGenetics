	initialize()
	{

	//set the overall mutation rate
	initializeMutationRate(1e-7) ;

	// m1 mutation type: neutral
	initializeMutationType("m1", 0.5, "f", 0.0) ;

	//genomic element type
	initializeGenomicElementType("g1", m1, 1.0) ;

	initializeGenomicElement(g1, 0, 99999) ;

	initializeRecombinationRate(1e-8) ;
	}

	1 {
		sim.addSubpop("p1", 323) ;
	}

//	500 {
//		p1.setSubpopulationSize(100) ;
//	}

//	1000 {
//		p1.setSubpopulationSize(500) ;
//	}

//	2000 {
//		p1.setSubpopulationSize(2000) ;
//	}

	//Harmonic mean is 322.6 (323)
	2500
	{
	sim.simulationFinished() ;
	}

	2500 late() {
	total = 0.0		;
	for (ind in p1.individuals) {
	muts0 = ind.genomes[0].mutations ;
	muts1 = ind.genomes[1].mutations ;

	shared_count = sum(match(muts0, muts1)>=0) ;

	unshared_count = muts0.size() + muts1.size() -2*shared_count ;

	pi_ind = unshared_count / (sim.chromosome.lastPosition+1) ;
	total = total + pi_ind					  ;
	}
	pi = total / p1.individuals.size() ;

	m = sortBy(unique(p1.genomes.mutations), "position") ;

	print(pi)		;
	print(size(m))		;
	}
