﻿using JoyReactor.Core.Model.DTO;
using SQLite.Net;

namespace JoyReactor.Core.Model.Database
{
    class CreateTablesTransaction
    {
        SQLiteConnection db;

        public CreateTablesTransaction(SQLiteConnection db)
        {
            this.db = db;
        }

        public void Execute()
        {
            db.CreateTable<Post>();
            db.CreateTable<Profile>();
            db.CreateTable<Tag>();
            db.CreateTable<TagPost>();
            db.CreateTable<Profile>();
            db.CreateTable<TagLinkedTag>();
            db.CreateTable<Comment>();
            db.CreateTable<Attachment>();
            db.CreateTable<CommentLink>();

            db.CreateTable<PrivateMessageThread>();
            db.CreateTable<PrivateMessage>();
        }
    }
}
