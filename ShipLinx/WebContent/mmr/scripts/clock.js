function TZClock()
{
	if (window.ClockInstance != null)
		{
		window.alert ("You may create only one TZClock object on a page.");
		return
		}
	window.ClockInstance = this;
	
	this.Attach = TZClock_Attach;
	this.Start = TZClock_Start;
	this.Stop = TZClock_Stop;
	this.GetFormattedTime = TZClock_DefaultGetFormattedTime;
	
	// internal properties
	this.interval = 1000;	// default to 1 second updates
	this.clocks = new Array();
	this.clockCount = 0;
	this.updateTimerID = null;
}

// Attach method
//		Use this method to attach any HTML element with an innerText property
//		or an INPUT text-type element to the list of elements to be
//		periodically updated.
//	Parameters:
//		sElementID
//			type: string
//			Set to the ID attribute of the element to be periodically updated
//		sTimeZoneOffset
//			type: string
//			Set to an empty string to use local time
//			Set to the ISO 8601 type of time zone offset, in this format:
//				{+|-}hh[:]mm
//				where:
//					{+|-} = required indicator of direction of offset from UTC
//					hh = hours offset from UTC
//					[:] = optional hours and minutes separator
//					mm = minutes offset from UTC
//				samples:
//					+01:00
//					-0230
//			IMPORTANT: This method does NOT apply Daylight Saving Time rules,
//				or summer rules or winter rules, to localized adjustments of the
//				time displayed. The rules for determining these offsets vary
//				widely around the world; they have been changed frequently by
//				local governments (down to the county level in the US, for instance)
//				and are likely to be changed in the future. There are no standards
//				for determining Daylight Saving Time in a reliable way.
//			examples:
//				for Pacfic Standard Time, use: -08:00
//				for Eastern Standard Time, use: -0400
//				for Chatham (New Zealand) Standard Time, use: +12:45
function TZClock_Attach(sElementID, sTimeZoneOffset)
{
	var elmClock = document.getElementById(sElementID);
	if (elmClock == null)
		{
		window.alert ("The element '" + sElementID + "' does not exist.");
		return;
		}
	if ((elmClock.innerText == null) || ((elmClock.tagName == "INPUT") && (elmClock.type != "text")))
		{
		window.alert ("The element '" + sElementID + "' is not an HTML element with an innerText property or an HTML text-type INPUT element.");
		return;
		}
	elmClock.timeZoneOffset = TZClock_MinutesFromTimeZoneOffset(sTimeZoneOffset);
	this.clocks[this.clockCount] = elmClock;
	this.clockCount++;
}

//	Start method
//		Use this method to begin updating the elements with the current time
//	Parameters:
//		nInterval
//			type: integer
//			Set to the number of seconds between updates
//			Set to 0 to default to one second
function TZClock_Start(nInterval)
{
	if (this.updateTimerID != null)
		this.Stop();
	if (nInterval == null)
		this.interval = 1000;
	else
		{
		if (nInterval > 0)
			this.interval = nInterval * 1000;
		else
			this.interval = 1000;
		}
	this.updateTimerID = window.setInterval(TZClock_OnUpdate, this.interval);
	TZClock_OnUpdate();
}

function TZClock_Stop()
{
	window.clearInterval(this.updateTimerID);
	this.updateTimerID = null;
}

function TZClock_OnUpdate()
{
	var clk = window.ClockInstance;
	for (var iClock = 0; iClock < clk.clockCount; iClock++)
		{
		var elmClock = clk.clocks[iClock];
		if (elmClock.tagName == "INPUT")
			TZClock_UpdateInput(elmClock);
		else
			TZClock_UpdateInnerText(elmClock);
		}
}

function TZClock_UpdateInput(elmClock)
{
	var dtmAdjusted = TZClock_GetAdjustedTime(elmClock.timeZoneOffset);
	elmClock.value = TZClock_FormattedTime(dtmAdjusted);
}

function TZClock_UpdateInnerText(elmClock)
{
	var dtmAdjusted = TZClock_GetAdjustedTime(elmClock.timeZoneOffset);
	elmClock.innerText = TZClock_FormattedTime(dtmAdjusted);
}

function TZClock_GetAdjustedTime(nTimeZoneOffset)
{
	var nowLocal = new Date();
	var nowAdjusted = new Date(nowLocal.getUTCFullYear(), nowLocal.getUTCMonth(), nowLocal.getUTCDate(), nowLocal.getUTCHours(), nowLocal.getUTCMinutes() + nTimeZoneOffset, nowLocal.getUTCSeconds(), nowLocal.getUTCMilliseconds());
	return nowAdjusted;
}

function TZClock_FormattedTime (dtm)
{
	var clk = window.ClockInstance;
	var fGetFormattedTime = clk.GetFormattedTime;
	return fGetFormattedTime(dtm);
}

function TZClock_DefaultGetFormattedTime(dtm)
{
	return dtm.toLocaleString();
}

function TZClock_MinutesFromTimeZoneOffset(sTimeZoneOffset)
{
	var c_sInvalidArg = "function TZClock_MinutesFromTimeZoneOffset\nInvalid argument: sTimeZoneOffset\nRequired format: {+|-}hh[:]mm.";
	if (sTimeZoneOffset.length == 0)
		return (-1 * (new Date().getTimezoneOffset()));
	else
		{
		var sOffsetDirection = sTimeZoneOffset.charAt(0);
		if ((sOffsetDirection == "+") || (sOffsetDirection == "-"))
			{
			var sHours = sTimeZoneOffset.substr(1, 2);
			var nHours = parseInt(sHours, 10);
			if (! isNaN(nHours))
				{
				var cchFirstMinute;
				var chSeparator = sTimeZoneOffset.charAt(3);
				cchFirstMinute = (chSeparator == ":") ? 4 : 3;
				var sMinutes = sTimeZoneOffset.substr(cchFirstMinute, 2);
				var nMinutes = parseInt(sMinutes, 10);
				if (! isNaN(nMinutes))
					{
					return ((sOffsetDirection == "-" ? -1 : 1) * nHours * 60 + nMinutes);
					}
				else
					{
					window.alert (c_sInvalidArg);
					return NaN;
					}
				}
			else
				{
				window.alert (c_sInvalidArg);
				return NaN;
				}
			}
		else
			{
			window.alert (c_sInvalidArg);
			return NaN;
			}
		}
}
