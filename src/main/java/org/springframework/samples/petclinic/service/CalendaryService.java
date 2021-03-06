/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Calendary;
import org.springframework.samples.petclinic.repository.CalendaryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CalendaryService {

	private CalendaryRepository calendaryRepository;


	@Autowired
	public CalendaryService(final CalendaryRepository calendaryRepository) {
		this.calendaryRepository = calendaryRepository;

	}

	@Transactional
	public void deleteCalendary(final Calendary calendary) throws DataAccessException {
		this.calendaryRepository.delete(calendary);
	}

	@Transactional
	public Calendary findById(final int i) {
		return this.calendaryRepository.findById(i);
	}

	@Transactional
	public Calendary findCalendaryByCompetitionId(final int competitionId) {
		return this.calendaryRepository.findCalendaryByCompetitionId(competitionId);
	}

	public void saveCalendary(final Calendary calendary) {
		this.calendaryRepository.save(calendary);

	}
}
