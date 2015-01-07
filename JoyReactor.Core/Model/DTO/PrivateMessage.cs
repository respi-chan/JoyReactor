﻿using System;
using SQLite.Net.Attributes;

namespace JoyReactor.Core.Model.DTO
{
	[Table ("messages")]
	public class PrivateMessage
	{
		public const int ModeInbox = 0;
		public const int ModeOutbox = 1;

		[PrimaryKey, AutoIncrement]
		public int Id { get; set; }

		public int ThreadId { get; set; }

		public string Message { get; set; }

		public DateTime Created { get; set; }

		public int Mode { get; set; }
	}
}