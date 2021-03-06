/*
 * Copyright 2012 The Play! Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package models;

import play.db.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@Entity
public class ModuleVersion extends Model
{
    @Id
    public Long id;

    @ManyToOne
    public Module module;

    // this is taken from Build.scala
    @Column(nullable = false)
    public String versionCode;

    @Column(nullable = false)
    public String releaseNotes;

    @Column(nullable = false)
    public Date releaseDate;

    // this is taken from Build.scala
    // organisation here or in {@link Module}? I think it makes more sense here
    @Column(nullable = false)
    public String organisation;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    public List<PlayVersion> compatibility;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    public BinaryContent binaryFile;

    @OneToOne(optional = true, fetch = FetchType.LAZY)
    public BinaryContent sourceFile;

    @OneToOne(optional = true, fetch = FetchType.LAZY)
    public BinaryContent documentFile;

    public static final Finder<Long, ModuleVersion> FIND = new Finder<Long, ModuleVersion>(Long.class,
                                                                                           ModuleVersion.class);
}
