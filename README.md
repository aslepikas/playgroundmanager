This library is intended as an application exercise.

##Usage

**Manager** - class used to register new playsites.

**TicketRegistry** - class used to register *Ticket*s.

**HistoryManager** - class used to manage kid playtime history - when they started and finished playing at particular playsites.

**KidHistoryRecord** - class representing a single playtime event. Managed by *HistoryManager*.

**SiteVisitorCounter** - class that manages number of visits for each playsite during a day. Stores history split by separate days.

**SnapshotManager** - a class that has an internal scheduler, doing snapshots during work hours only (using *TimeUtil*). Data represented as *UtilisationRecord*s.

**Ticket** - class used to track VIP status. Each ticket is assigned to a kid via an ID. Each Ticket needs to be registered to *TicketRegistry*

**Kid** - a class defining a kid. Each kid has a *waiting* trait - it determines whether or not the kid accepts waiting in lines.

**Playsite** - abstract class that has the internal logic for queueing children. This class automatically registers play counts in *SiteVisitorCounter*. Also automatically creates new *KidHistoryRecord*s
